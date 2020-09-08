package com.shenzou.workoutchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    val exercicesList = mutableListOf<Exercice>()
    val musclesList = mutableListOf<Muscle>()
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Init()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.contentTab)

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MenuAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter
        viewPager!!.currentItem = 0

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    fun Init(){
        //Intialisation des muscles
        val biceps = Muscle("Biceps")
        val triceps = Muscle("Triceps")
        val pectoraux = Muscle("Pectoraux")
        val epaules = Muscle("Epaules")
        val avantbras = Muscle("Avant-bras")
        val dorsaux = Muscle("Dorsaux")
        val trapezes = Muscle("Trapezes")
        val lombaires = Muscle("Lombaires")
        val abdominaux = Muscle("Abdominaux")
        val quadriceps = Muscle("Quadriceps")
        val fessiers = Muscle("Fessiers")
        val ischio = Muscle("Ischio-jambiers")
        val mollets = Muscle("Mollets")

        //Initialisation des exercices
        var tempList = mutableListOf<Muscle>()
        var tempListSecond = mutableListOf<Muscle>()

        tempList.add(fessiers)
        tempListSecond.add(abdominaux)
        exercicesList.add(Exercice("Abducteurs machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Abducteurs à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Abducteurs allongé", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(fessiers)
        exercicesList.add(Exercice("Abducteurs assis", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Adducteurs machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Adducteurs à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Adducteurs assis", "", tempList, tempListSecond))
        tempList.clear()

        tempList.add(triceps)
        exercicesList.add(Exercice("Barre au front", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Barre au front allongé poulie basse", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Extension des triceps à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Extension des triceps à la poulie à un bras", "", tempList, tempListSecond))
        tempList.clear()

        tempList.add(avantbras)
        tempListSecond.add(epaules)
        exercicesList.add(Exercice("Bobine Andrieux - Extension", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Bobine Andrieux - Flexion", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(mollets)
        exercicesList.add(Exercice("Chameau", "", tempList, tempListSecond))
        tempList.clear()

        tempList.add(abdominaux)
        exercicesList.add(Exercice("Crunch", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch à la poulie haute", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch avec abmat", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch avec rotation", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch oblique", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Crunch Swiss Ball", "", tempList, tempListSecond))
        tempList.clear()

        tempList.add(biceps)
        tempListSecond.add(avantbras); tempListSecond.add(abdominaux); tempListSecond.add(lombaires)
        exercicesList.add(Exercice("Curl à la barre", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl à la poulie basse", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl avec haltères", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(biceps)
        tempListSecond.add(avantbras)
        exercicesList.add(Exercice("Curl à la poulie vis à vis", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl allongé à la poulie basse", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl allongé à la poulie haute", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl araignée", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl au pupitre", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl au pupitre à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl au pupitre à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl concentré", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl incliné", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé allongé à la poulie basse", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé allongée à la poulie haute", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé au pupitre", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl inversé au pupitre à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl marteau", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Curl marteau en travers", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(triceps)
        tempListSecond.add(trapezes); tempListSecond.add(biceps); tempListSecond.add(lombaires)
        exercicesList.add(Exercice("Développé à l'envers", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(pectoraux); tempList.add(triceps)
        tempListSecond.add(trapezes); tempListSecond.add(biceps)
        exercicesList.add(Exercice("Développé Arnold", "", tempList, tempListSecond))
        tempListSecond.add(dorsaux)
        exercicesList.add(Exercice("Développé assis à la machine convergente", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(triceps)
        tempListSecond.add(trapezes); tempListSecond.add(biceps)
        exercicesList.add(Exercice("Développé avec haltères", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé nuque", "", tempList, tempListSecond))
        tempList.add(pectoraux)
        tempListSecond.add(dorsaux)
        exercicesList.add(Exercice("Développé couché", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé couché à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé couché à la machine convergente", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé couché avec haltères", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé couché prise serrée", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé décliné", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé décliné avec haltères", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé incliné", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé incliné à la machine convergente", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé incliné avec haltères", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Dips à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Dips entre deux bancs", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Dips prise large buste penché", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Dips prise serrée", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(triceps)
        tempListSecond.add(trapezes); tempListSecond.add(biceps)
        exercicesList.add(Exercice("Développé épaules à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Développé épaules à la machine convergente", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(pectoraux); tempList.add(triceps)
        tempListSecond.add(trapezes); tempListSecond.add(lombaires)
        exercicesList.add(Exercice("Développé militaire", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(abdominaux)
        exercicesList.add(Exercice("Drapeau du dragon", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Enroulement de bassin", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Enroulement de bassin avec l'abmat", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Enroulement de bassin suspendu à la barre", "", tempList, tempListSecond))
        tempList.clear()

        tempList.add(pectoraux)
        tempListSecond.add(epaules); tempListSecond.add(triceps); tempListSecond.add(biceps)
        exercicesList.add(Exercice("Ecarté à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Ecarté à la poulie vis à vis haute", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Ecarté couché", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Ecarté décliné", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Ecarté incliné", "", tempList, tempListSecond))
        tempList.add(epaules)
        tempListSecond.clear()
        tempListSecond.add(biceps)
        exercicesList.add(Exercice("Ecarté à la poulie vis à vis basse", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(epaules); tempList.add(pectoraux)
        tempListSecond.add(trapezes); tempListSecond.add(biceps); tempListSecond.add(avantbras)
        exercicesList.add(Exercice("Elévation frontale à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation frontale avec haltères", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation frontale avec barre", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation frontale sur banc incliné", "", tempList, tempListSecond))
        tempList.clear()
        tempList.add(epaules)
        exercicesList.add(Exercice("Elévation latérale à la machine", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation latérale à la poulie", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation latérale à un bras penché", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation latérale à un bras sur banc incliné", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Elévation latérale avec haltères", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(lombaires)
        tempListSecond.add(ischio); tempListSecond.add(fessiers)
        exercicesList.add(Exercice("Enroulement/déroulement au banc à lombaires", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(ischio); tempList.add(fessiers)
        tempListSecond.add(lombaires); tempListSecond.add(mollets)
        exercicesList.add(Exercice("Extension au banc à lombaires à 45 degrés", "", tempList, tempListSecond))
        exercicesList.add(Exercice("Extension au banc à lombaires à 90 degrés", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(fessiers)
        tempListSecond.add(ischio)
        exercicesList.add(Exercice("Extension de la hanche à la machine", "", tempList, tempListSecond))
        tempList.clear()
        tempListSecond.clear()

        tempList.add(avantbras)
        exercicesList.add(Exercice("Extension des poignets", "", tempList, tempListSecond))

    }
}