package balloh.khabib.ahmad.pml13

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import balloh.khabib.ahmad.pml13.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), View.OnClickListener,
    NavigationBarView.OnItemSelectedListener {

    lateinit var b: ActivityMainBinding
    lateinit var ft: FragmentTransaction
    lateinit var fragmentWebView: FragmentWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        fragmentWebView = FragmentWebView()

        b.btnYt.setOnClickListener(this)
        b.btnMessage.setOnClickListener(this)
        b.btnTiktok.setOnClickListener(this)
        b.bottomNavigationView.setOnItemSelectedListener(this)
    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)  // Inflating the menu resource
        return true
    }

    // Handle menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemHome -> {
                // Your logic for Home item
                b.frameLayout.visibility = View.GONE
            }
            R.id.itemLoc -> {
                // Your logic for Lokasi item
                b.frameLayout.visibility = View.VISIBLE
                val url = "https://www.google.com/maps"
                replaceFragment(url)
            }
            R.id.itemShop -> {
                // Your logic for Shop item
                b.frameLayout.visibility = View.VISIBLE
                val url = "https://www.shopee.co.id"
                replaceFragment(url)
            }
            R.id.itemProfil -> {
                // Your logic for Profil item
                b.frameLayout.visibility = View.VISIBLE
                val url = "https://kangabib.github.io/Portofolio/"
                replaceFragment(url)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnYt -> replaceFragment("https://youtube.com")
            R.id.btnTiktok -> replaceFragment("https://tiktok.com")
            R.id.btnMessage -> replaceFragment("https://ujicobawebapp.000webhostapp.com/form_send_message.php")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemHome -> b.frameLayout.visibility = View.GONE
            else -> {
                b.frameLayout.visibility = View.VISIBLE
                val url = when (item.itemId) {
                    R.id.itemLoc -> "https://www.google.com/maps/place/PSDKU+POLINEMA+di+Kota+Kediri+kampus+2/@-7.8025674,111.9798355,17z/data=!3m1!4b1!4m6!3m5!1s0x2e7856c8ee550497:0x3f8ff2e0bc9b9718!8m2!3d-7.8025674!4d111.9798355!16s%2Fg%2F11c6f2rz1c?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D"
                    R.id.itemProfil -> "https://kangabib.github.io/Portofolio/"
                    R.id.itemShop -> "https://www.shopee.co.id"
                    else -> ""
                }
                replaceFragment(url)
            }
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (b.frameLayout.isVisible) {
            if (keyCode == KeyEvent.KEYCODE_BACK && fragmentWebView.b.webV.canGoBack()) {
                fragmentWebView.b.webV.goBack()
                return true
            } else {
                b.frameLayout.visibility = View.GONE
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun replaceFragment(url: String) {
        GlobalVariables.url = url

        // Buat instance baru FragmentWebView
        fragmentWebView = FragmentWebView()

        // Tampilkan frameLayout
        b.frameLayout.visibility = View.VISIBLE

        // Ganti fragment dengan instance baru
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout, fragmentWebView).commit()
    }
}
