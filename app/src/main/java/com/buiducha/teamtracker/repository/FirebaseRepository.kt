package com.buiducha.teamtracker.repository

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.buiducha.teamtracker.data.model.Notification
import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.data.model.project.Board
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.data.model.project.TaskMember
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.data.model.user.UserData
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID
import kotlin.math.log

class FirebaseRepository private constructor(context: Context) {
    private var auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database
    private val usersRef = database.getReference("users")
    private val workspacesRef = database.getReference("workspaces")
    private val workspaceMemberRef = database.getReference("workspace_member")
    private val messagesRef = database.getReference("messages")
    private val postsRef = database.getReference("posts")
    private val boardsRef = database.getReference("boards")
    private val tasksRef = database.getReference("tasks")
    private val taskMemberRef = database.getReference("task_member")
    private val notificationRef = database.getReference("notification")
    private val storage = com.google.firebase.Firebase.storage
    private var storageRef = storage.reference

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
                    onRemoveSuccess()
                }

                override fun onCancelled(error: DatabaseError) {
                    onRemoveFailure()
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

    fun getWorkspaceMemberId(
        workspaceId: String,
        onGetMemberSuccess: (MutableList<String>) -> Unit,
        onGetMemberFailure: () -> Unit
    ) {
        workspaceMemberRef.orderByChild("workspaceId").equalTo(workspaceId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val members = mutableListOf<String>()
                    snapshot.children.forEach { shot ->
                        val memberId = shot.child("userId").getValue(String::class.java)
                        if (memberId != null) {
                            members.add(memberId)
                        }
                    }

                    onGetMemberSuccess(members)
                }

                override fun onCancelled(error: DatabaseError) {
                    onGetMemberFailure()
                }

            })
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

    fun updateWorkspace(
        uri: Uri?,
        workspace: Workspace,
        onUpdateSuccess: () -> Unit,
        onUpdateFailure: () -> Unit
    ) {
        workspacesRef.orderByChild("id").equalTo(workspace.id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { shot ->
                        shot.ref.child("name").setValue(workspace.name)
                        shot.ref.child("describe").setValue(workspace.describe)
                        if (uri != null) {
                            shot.ref.child("avatar").setValue(workspace.avatar)
                        }
                    }
                    onUpdateSuccess()
                }

                override fun onCancelled(error: DatabaseError) {
                    onUpdateFailure()
                }
            })
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
            .addValueEventListener(object : ValueEventListener {
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

    fun updateUserInfo(
        userData: UserData,
    ) {
        usersRef.orderByChild("id").equalTo(auth.currentUser?.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    val key = shot.key
                    key?.let {
                        usersRef.child(key).setValue(userData).addOnCompleteListener {
                            Log.d(TAG, "updateUserInfo: ")
                        }
                        Log.d(TAG, key)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
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

    fun forgotPassword(
        email: String,
        onSendEmailSuccess: () -> Unit,
        onSendEmailFailure: () -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    onSendEmailSuccess()
                }
            }
            .addOnFailureListener {e ->
                onSendEmailFailure()
                Log.e(TAG, "send email failure", e)
            }
    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        onChangePasswordSuccess: () -> Unit,
        onChangePasswordFailure: (String) -> Unit,
    ) {
        val credential = EmailAuthProvider.getCredential(
            auth.currentUser?.email ?: "",
            oldPassword
        )

        auth.currentUser?.reauthenticate(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Call updatePassword() method to update the password
                    updatePassword(
                        newPassword = newPassword,
                        onChangePasswordSuccess = onChangePasswordSuccess,
                        onChangePasswordFailure = onChangePasswordFailure
                    )
                    Log.d(TAG, "changePassword: true")
                } else {
                    // Handle the error
                    onChangePasswordFailure(OLD_PASSWORD_INVALID)
                    Log.d(TAG, "changePassword: false")
                }
            }
    }

    private fun updatePassword(
        newPassword: String,
        onChangePasswordSuccess: () -> Unit,
        onChangePasswordFailure: (String) -> Unit
    ) {
        auth.currentUser?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Password updated successfully
                    onChangePasswordSuccess()
                    Log.d(TAG, "update password successfully")
                } else {
                    // Handle the error
                    onChangePasswordFailure(PASSWORD_CHANGE_FAILURE)
                    Log.d(TAG, "update password failure")
                }
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

    fun userLogout() {
        auth.signOut()
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
                    onGetPostsFailure()
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

    fun deletePost(
        postId: String
    ) {
        postsRef.orderByChild("id").equalTo(postId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { post ->
                    post.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
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

    fun uploadImageToStorage(uri: Uri, context: Context, imgUrl: MutableState<String>, oldImage: String) {
        val uniqueImageName: UUID? = UUID.randomUUID()
        val spaceRef: StorageReference = storageRef.child("images/$uniqueImageName.jpg")

        val byteArray: ByteArray? = context.contentResolver
            .openInputStream(uri)
            ?.use { it.readBytes() }

        byteArray?.let {

            val uploadTask = spaceRef.putBytes(byteArray)
            uploadTask.addOnFailureListener {
                Toast.makeText(context,"upload failed", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {task ->
                task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    imgUrl.value = it.toString()
                    storageRef.child("images/$oldImage").delete()
                }
            }
        }
    }

    fun getBoards(
        workspaceId: String,
        onGetBoardsSuccess: (MutableList<Board>) -> Unit,
        onGetBoardsFailure: () -> Unit
    ) {
        boardsRef.orderByChild("workspaceId").equalTo(workspaceId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val boardsList = mutableListOf<Board>()
                    snapshot.children.forEach { shot ->
                        val board = shot.getValue<Board>(Board::class.java)
                        board?.let {
                            boardsList += it
                        }
                    }
                    onGetBoardsSuccess(boardsList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun deleteBoard(
        boardId: String
    ) {
        Log.d(TAG, "deleteBoard: $boardId")
        boardsRef.orderByChild("id").equalTo(boardId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { post ->
                    post.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


    fun createBoard(
        board: Board,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        boardsRef.push().setValue(board)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "create board success")
                    onCreateSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add board failure", e)
                onCreateFailure()
            }
    }

    fun addTask(
        task: Task,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        tasksRef.push().setValue(task)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "create board success")
                    onAddSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add board failure", e)
                onAddFailure()
            }
    }

    fun getTask(
        taskId: String,
        onGetDataSuccess: (Task) -> Unit
    ) {
        tasksRef.orderByChild("id").equalTo(taskId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    val data = shot.getValue(Task::class.java)
                    data?.let(onGetDataSuccess)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun getTasks(
        boardId: String,
        onGetTaskSuccess: (List<Task>) -> Unit
    ) {
        tasksRef.orderByChild("boardId").equalTo(boardId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val taskList = mutableListOf<Task>()
                    snapshot.children.forEach { shot ->
                        val task = shot.getValue(Task::class.java)
                        task?.let {
                            taskList += it
                        }
                    }
                    onGetTaskSuccess(taskList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun updateTask(
        task: Task,
        onUpdateSuccess: () -> Unit,
        onUpdateFailure: () -> Unit
    ) {
        tasksRef.orderByChild("id").equalTo(task.id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { shot ->
                        shot.ref.child("description").setValue(task.description)
                        shot.ref.child("title").setValue(task.title)
                        shot.ref.child("tag").setValue(task.tag)
                        if (task.startDate != null) {
                            shot.ref.child("startDate").setValue(task.startDate)
                        }
                        if (task.dueDate != null) {
                            shot.ref.child("dueDate").setValue(task.dueDate)
                        }
                    }
                    Log.d(TAG, "update task success")
                    onUpdateSuccess()
                }

                override fun onCancelled(error: DatabaseError) {
                    onUpdateFailure()
                }
            })
    }

    fun getTaskMember(
        taskId: String,
        onGetMemberSuccess: (MutableList<UserData>) -> Unit,
        onGetMemberFailure: () -> Unit
    ) {
        taskMemberRef.orderByChild("taskId").equalTo(taskId)
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

    fun removeMemberFromTask(
        taskMember: TaskMember,
        onRemoveSuccess: () -> Unit,
        onRemoveFailure: () -> Unit
    ) {
        taskMemberRef.orderByChild("taskId")
            .equalTo(taskMember.taskId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { shot ->
                        val memberId = shot.child("userId").getValue(String::class.java)
                        if (memberId == taskMember.userId) {
                            shot.ref.removeValue()
                        }
                    }
                    onRemoveSuccess()
                }

                override fun onCancelled(error: DatabaseError) {
                    onRemoveFailure()
                }
            })
    }

    fun addMemberToTask(
        taskMember: TaskMember,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        taskMemberRef.push().setValue(taskMember)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "add member to task success")
                    onAddSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "add member to task failure", e)
                onAddFailure()
            }
    }

    fun updateUserAvatar(
        avatarUri: MutableState<String>
    ) {
        usersRef.orderByChild("id").equalTo(getCurrentUser()?.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { shot ->
                            shot.ref.child("avatarUri").setValue(avatarUri.value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

//    fun updateWorkspace(
//        uri: Uri?,
//        workspace: Workspace,
//        onUpdateSuccess: () -> Unit,
//        onUpdateFailure: () -> Unit
//    ) {
//        workspacesRef.orderByChild("id").equalTo(workspace.id)
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    snapshot.children.forEach { shot ->
//                        shot.ref.child("name").setValue(workspace.name)
//                        shot.ref.child("describe").setValue(workspace.describe)
//                        if (uri != null) {
//                            shot.ref.child("avatar").setValue(workspace.avatar)
//                        }
//                    }
//                    onUpdateSuccess()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    onUpdateFailure()
//                }
//            })
//    }
//Notification
fun getNotifications(
    onGetNotificationSuccess:(MutableList<Notification>)-> Unit
){
    notificationRef.orderByChild("receiverId").equalTo(getCurrentUser()?.uid)
        .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val notificationList = mutableListOf<Notification>()
                snapshot.children.forEach {
                        shot ->
                    val notification = shot.getValue(Notification::class.java)
                    notification?.let {
                        notificationList += it
                    }
                }
                onGetNotificationSuccess(notificationList)
            }


            override fun onCancelled(error: DatabaseError) {


            }
        })
    getCurrentUser()?.let { Log.d("loguserId", "usrid: " + it.uid) }
}


    fun createNotification(notification: Notification) {
        notificationRef.push().setValue(notification)
            .addOnCompleteListener{
                Log.d(TAG, "create notification success")
            }
            .addOnFailureListener{
                Log.e(TAG, "create notification failure")
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

        const val OLD_PASSWORD_INVALID = "old_password_invalid"
        const val PASSWORD_CHANGE_FAILURE = "password_change_failure"
    }
}