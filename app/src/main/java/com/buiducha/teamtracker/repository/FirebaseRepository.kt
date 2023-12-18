package com.buiducha.teamtracker.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.data.model.user.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepository private constructor(context: Context) {
    private var auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database
    private val usersRef = database.getReference("users")
    private val workspacesRef = database.getReference("workspaces")
    private val workspaceMemberRef = database.getReference("workspace_member")
    private val messagesRef = database.getReference("messages")
    private val postsRef = database.getReference("posts")


    fun removeMemberFromWorkspace(
        workspaceMember: WorkspaceMember,
        onRemoveSuccess: () -> Unit,
        onRemoveFailure: () -> Unit
    ) {
        workspaceMemberRef.orderByChild("workspaceId")
            .equalTo(workspaceMember.workspaceId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { shot ->
                        val memberId = shot.child("userId").getValue(String::class.java)
                        if (memberId == workspaceMember.userId) {
                            shot.ref.removeValue()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    fun addMemberToWorkspace(
        workspaceMember: WorkspaceMember,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        workspaceMemberRef.push().setValue(workspaceMember)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "add member to workspace success")
                    onAddSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add member to workspace failure", e)
                onAddFailure()
            }
    }

    fun getWorkspaceMember(
        workspaceId: String,
        onGetMemberSuccess: (MutableList<UserData>) -> Unit,
        onGetMemberFailure: () -> Unit
    ) {
        workspaceMemberRef.orderByChild("workspaceId").equalTo(workspaceId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val members = mutableListOf<String>()
                    snapshot.children.forEach { shot ->
                        val memberId = shot.child("userId").getValue(String::class.java)
                        if (memberId != null) {
                            members.add(memberId)
                        }
                    }

                    usersRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val dataList = mutableListOf<UserData>()
                            snapshot.children.forEach { shot ->
                                val data = shot.getValue(UserData::class.java)
                                data?.let { member ->
                                    if (members.contains(member.id)) {
                                        dataList += member
                                    }
                                }
                                Log.d(TAG, "onDataChange: $data")
                            }
                            onGetMemberSuccess(dataList)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onGetMemberFailure()
                        }
                    })
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    fun getWorkspaces(
        onGetWorkspaceSuccess: (MutableList<Workspace>) -> Unit,
        onGetWorkspaceFailure: () -> Unit
    ) {

        workspaceMemberRef.orderByChild("userId").equalTo(auth.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val teams = mutableListOf<String>()

                    snapshot.children.forEach { shot ->
                        val teamId = shot.child("workspaceId").getValue(String::class.java)
                        if (teamId != null) {
                            teams.add(teamId)
                        }
                    }

                    workspacesRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val dataList = mutableListOf<Workspace>()
                            snapshot.children.forEach { shot ->
                                val data = shot.getValue(Workspace::class.java)
                                data?.let { workspace ->
                                    if (teams.contains(workspace.id)) {
                                        dataList += workspace
                                    }
                                }
                                Log.d(TAG, "onDataChange: $data")
                            }
                            onGetWorkspaceSuccess(dataList)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onGetWorkspaceFailure()
                        }
                    })

                    Log.d(TAG, "get workspace id $teams")
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun deleteWorkspace(
        workspaceId: String
    ) {
        workspacesRef.orderByChild("id").equalTo(workspaceId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { workspace ->
                    workspace.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun createWorkspace(
        workspace: Workspace,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        workspacesRef.push().setValue(workspace)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "create workspace success")
                    onCreateSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add workspace failure", e)
                onCreateFailure()
            }
    }

    fun getCurrentUser() = auth.currentUser

    fun getUserInfoList(
        onGetInfoSuccess: (List<UserData>) -> Unit,
        onGetInfoFailure: () -> Unit
    ) {
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<UserData>()
                snapshot.children.forEach { shot ->
                    val data = shot.getValue(UserData::class.java)
                    data?.let { user ->
                        userList += user
                    }
                }
                onGetInfoSuccess(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                onGetInfoFailure()
            }

        })
    }

    fun getUserInfo(
        userId: String,
        onGetInfoSuccess: (UserData) -> Unit,
        onGetInfoFailure: () -> Unit
    ) {
        Log.d(TAG, "getUserInfo: $userId")
        usersRef.orderByChild("id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { user ->
                        val data = user.getValue(UserData::class.java)
                        data?.let(onGetInfoSuccess)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onGetInfoFailure()
                }

            })
    }

    fun isUserInfoExists(
        userId: String,
        onUserExists: () -> Unit,
        onUserNotExists: () -> Unit
    ) {
        Log.d(TAG, "isUserInfoExists: ")
        usersRef.orderByChild("id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d(TAG, snapshot.value.toString())
                    if (snapshot.exists()) {
                        onUserExists()
                        Log.d(TAG, "user exists")
                    } else {
                        onUserNotExists()
                        Log.d(TAG, "user not exists")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "onCancelled: can't check")
                }

            })
    }

    fun addUserInfo(
        userData: UserData,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        usersRef.push().setValue(userData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "add user success")
                    onAddSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add user failure", e)
                onAddFailure()
            }
    }

    fun createUser(
        activity: Activity,
        email: String,
        password: String,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onCreateSuccess()
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                    Log.d(TAG, "create user successfully")
                } else if (task.isCanceled) {
                    Log.d(TAG, "create user failure")
                    onCreateFailure()
                }
            }
            .addOnFailureListener {
                onCreateFailure()
            }
    }

    fun userLogin(
        activity: Activity,
        email: String,
        password: String,
        onLoginSuccess: () -> Unit,
        onLoginFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        if (it.isEmailVerified) {
                            Log.d(TAG, "login successfully")
                            onLoginSuccess()
                        } else {
                            Log.d(TAG, "email not verified")
                            onLoginFailure("Email is not verified")
                        }
                    }
                } else if (task.isCanceled) {
                    Log.d(TAG, "login failure")
                }
            }
            .addOnFailureListener(activity) { _ ->
                Log.d(TAG, "login failure")
                onLoginFailure("Login failure")
            }
    }

    fun getPosts(
        workspaceId: String,
        onGetPostsSuccess: (MutableList<WorkspacePost>) -> Unit,
        onGetPostsFailure: () -> Unit
    ) {
        postsRef.orderByChild("workspaceId").equalTo(workspaceId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val postsList = mutableListOf<WorkspacePost>()
                    snapshot.children.forEach { shot ->
                        val post = shot.getValue(WorkspacePost::class.java)
                        post?.let {
                            postsList += it
                        }
                    }
                    onGetPostsSuccess(postsList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun getMessages(
        postId: String,
        onGetMessagesSuccess: (MutableList<PostMessage>) -> Unit
    ) {
        messagesRef.orderByChild("postId").equalTo(postId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messagesList = mutableListOf<PostMessage>()
                    snapshot.children.forEach { shot ->
                        val message = shot.getValue(PostMessage::class.java)
                        message?.let {
                            messagesList += it
                        }
                    }
                    onGetMessagesSuccess(messagesList)
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun sendMessage(
        message: PostMessage
    ) {
        messagesRef.push().setValue(message)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "create message success")
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add message failure", e)
            }
    }

    fun createPost(
        post: WorkspacePost,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        postsRef.push().setValue(post)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "create post success")
                    onCreateSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add post failure", e)
                onCreateFailure()
            }
    }

    companion object {
        const val TAG = "FirebaseRepository"
        private var INSTANCE: FirebaseRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FirebaseRepository(context)
            }
        }

        fun get(): FirebaseRepository {
            return INSTANCE ?: throw IllegalStateException("repo must be init")
        }
    }
}