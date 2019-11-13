package david.app.proyectokotlin.Onboarding

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import david.app.proyectokotlin.MainActivity
import david.app.proyectokotlin.OnBoarder.Entity.AhoyOnboarderCard
import david.app.proyectokotlin.OnBoarder.OnBoarderActivity
import david.app.proyectokotlin.R
import java.util.ArrayList

class ImageBackgroundActivity : OnBoarderActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ahoyOnboarderCard1 =
            AhoyOnboarderCard(
                "Link Cards", "Sign up for free by activating your credit cards.",
                R.drawable.spend
            )
        val ahoyOnboarderCard2 = AhoyOnboarderCard(
            "Dine Out",
            "Choose from 100's of restaurants with new spots added daily.",
            R.drawable.food
        )
        val ahoyOnboarderCard3 = AhoyOnboarderCard(
            "Get Cashback",
            "Earn upto 30% each time you dine with linked cards in network.",
            R.drawable.reward
        )

        ahoyOnboarderCard1.backgroundColor = (R.color.black_transparent)
        ahoyOnboarderCard2.backgroundColor = (R.color.black_transparent)
        ahoyOnboarderCard3.backgroundColor = (R.color.black_transparent)

        val pages = ArrayList<AhoyOnboarderCard>()

        pages.add(ahoyOnboarderCard1)
        pages.add(ahoyOnboarderCard2)
        pages.add(ahoyOnboarderCard3)

        for (page in pages) {
            page.titleColor = R.color.colorWhite
            page.descriptionColor = R.color.grey_200
        }

        setFinishButtonTitle("Get Started")
        showNavigationControls(true)

        setImageBackground(R.drawable.download)
        val face = Typeface.createFromAsset(assets, "fonts/Roboto-Light.ttf")
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorWhite);

        setOnboardPages(pages)

    }


    override fun onFinishButtonPressed() {
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


}