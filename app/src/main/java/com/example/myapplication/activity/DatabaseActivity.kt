package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.database.Database
import com.example.myapplication.model.User
import io.ebean.DB
import io.ebean.datasource.DataSourceConfig
import com.example.myapplication.domain.User as DomainUser
import io.ebean.EbeanServerFactory
import io.ebean.config.dbplatform.sqlite.SQLitePlatform
import io.ebean.config.ServerConfig
import java.sql.Connection


class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val database = Database(this)

        if (database.getUsersCount() == 0) {
            database.createUser(User("Bob", 10, "bob@kotlin.training"))
            database.createUser(User("Bobette", 4, "bobette@kotlin.training"))
            database.createUser(User("Mike", 14, "mike@kotlin.training"))
            database.createUser(User("Jane", 17, "jane@kotlin.training"))
        }

        var users = database.getAllUsers()
        for (user in users) {
            Log.i("DATABASE", "Utilisateur : $user")
        }

        val ds = DataSourceConfig()
        ds.driver = "org.sqlite.JDBC"
        ds.url = "jdbc:sqlite:data"
        ds.username = ""
        ds.password = ""
        ds.isolationLevel = Connection.TRANSACTION_SERIALIZABLE

        val config = ServerConfig()
        config.name = "Sqlite"
        config.isDdlGenerate = true
        config.isDdlRun = true
        config.isAutoCommitMode = true
        config.isDefaultServer = true
        config.databasePlatform = SQLitePlatform()
        //config.databasePlatform.getDbDdlSyntax().setIdentity("")
        config.dataSourceConfig = ds

        val server = EbeanServerFactory.create(config)
        val myUser = DomainUser()
        myUser.name = "Api"
        myUser.age = 10
        myUser.email = "api@api.fr"

        DB.save(myUser)
    }
}
