package com.example.safecar


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


private const val REQUEST_SIGN_IN = 12345
lateinit var mGoogleSignInClient: GoogleSignInClient
private lateinit var auth: FirebaseAuth
val Req_Code:Int=123
var firebaseAuth= FirebaseAuth.getInstance()


class LoginActivity : AppCompatActivity() {


    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth



       // mGoogleSignInClient.signOut()
        val btn = findViewById<View>(R.id.btn_google) as SignInButton
        btn.setOnClickListener { view: View? ->
            signInGoogle()
        }
        val btn_registo = findViewById<Button>(R.id.btn_registo)
        btn_registo.setOnClickListener { view: View? ->
            startActivity(Intent(this, RegistarActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("704467391456-tfkrpukra6tm28hmujs7jc8ofkehesvc.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // getting the value of gso inside the GoogleSigninClient
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        //mGoogleSignInClient.signOut()
        firebaseAuth = FirebaseAuth.getInstance()

        val btn_login = findViewById<Button>(R.id.btn_login) as MaterialButton

        btn_login.setOnClickListener{
                login()
        }
    }


    private fun login() {
        val email = findViewById<TextView>(R.id.txt_mail) as TextView
        val passe = findViewById<TextView>(R.id.txt_pass) as TextView
        println(email.text.toString())
        println(passe.text.toString())
        auth.signInWithEmailAndPassword(email.text.toString(), passe.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        println("signInWithEmail:success")
                        val intent = Intent(this,MainActivity::class.java)
                        val user = auth.currentUser
                        startActivity(intent)
                        //UpdateUI2(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        println("signInWithEmail:failure")
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //UpdateUI2(null)
                        // ...
                    }

                    // ...
                }
    }

//    private fun UpdateUI2(user: FirebaseUser?) {
//        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
//            if(task.isSuccessful) {
//                SavedPreference.setEmail(this,account.email.toString())
//                SavedPreference.setUsername(this,account.displayName.toString())
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//    }

    // signInGoogle() function
    private  fun signInGoogle(){

        val signInIntent:Intent=mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,Req_Code)
    }
    // onActivityResult() function : this is where we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e:ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
        }
    }
    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun UpdateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
                SavedPreference.setEmail(this,account.email.toString())
                SavedPreference.setUsername(this,account.displayName.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        // if you do not add this check, then you would have to login everytime you start your application on your phone.
    }
}

//        val btn_click_me = findViewById(R.id.btn_registo) as Button
//        // set on-click listener
//        btn_click_me.setOnClickListener {
//            Toast.makeText(this, "You clicked me.", Toast.LENGTH_SHORT).show()
//        }
//        setup()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == REQUEST_SIGN_IN && resultCode == Activity.RESULT_OK){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//    }
//
//    private fun setup() {
//        findViewById<SignInButton>(R.id.btn_google).setOnClickListener() {
//
//            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .build(),
//                REQUEST_SIGN_IN
//            )
//        }
//    }


