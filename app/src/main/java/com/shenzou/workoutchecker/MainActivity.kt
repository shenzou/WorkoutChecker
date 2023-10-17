package com.shenzou.workoutchecker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shenzou.workoutchecker.adapters.MenuAdapter
import com.shenzou.workoutchecker.objects.Exercice
import com.shenzou.workoutchecker.objects.Muscle

class MainActivity : AppCompatActivity() {


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
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }



    companion object Exercices{
        val exercicesList = ArrayList<Exercice>()
        val musclesList = ArrayList<Muscle>()



        fun Init(){
            //Intialisation des muscles
            val biceps = Muscle("Biceps", R.drawable.muscle_biceps, R.drawable.muscle_biceps_s)
            val triceps = Muscle("Triceps", R.drawable.muscle_triceps, R.drawable.muscle_triceps_s)
            val pectoraux = Muscle("Pectoraux", R.drawable.muscle_pectoraux, R.drawable.muscle_pectoraux_s)
            val epaules = Muscle("Epaules", R.drawable.muscle_epaules, R.drawable.muscle_epaules_s)
            val avantbras = Muscle("Avant-bras", R.drawable.muscle_avantbras, R.drawable.muscle_avantbras_s)
            val dorsaux = Muscle("Dorsaux", R.drawable.muscle_dorsaux, R.drawable.muscle_dorsaux_s)
            val trapezes = Muscle("Trapezes", R.drawable.muscle_trapezes, R.drawable.muscle_trapezes_s)
            val lombaires = Muscle("Lombaires", R.drawable.muscle_lombaires, R.drawable.muscle_lombaires_s)
            val abdominaux = Muscle("Abdominaux", R.drawable.muscle_abs, R.drawable.muscle_abs_s)
            val quadriceps = Muscle("Quadriceps", R.drawable.muscle_quadriceps, R.drawable.muscle_quadriceps_s)
            val fessiers = Muscle("Fessiers", R.drawable.muscle_fessier, R.drawable.muscle_fessier_s)
            val ischio = Muscle("Ischio-jambiers", R.drawable.muscle_ischio, R.drawable.muscle_ischio_s)
            val mollets = Muscle("Mollets", R.drawable.muscle_mollets, R.drawable.muscle_mollets_s)
            musclesList.add(biceps)
            musclesList.add(triceps)
            musclesList.add(pectoraux)
            musclesList.add(epaules)
            musclesList.add(avantbras)
            musclesList.add(dorsaux)
            musclesList.add(trapezes)
            musclesList.add(lombaires)
            musclesList.add(abdominaux)
            musclesList.add(quadriceps)
            musclesList.add(fessiers)
            musclesList.add(ischio)
            musclesList.add(mollets)

            //Initialisation des exercices
            val tempList = ArrayList<Muscle>()
            val tempListSecond = ArrayList<Muscle>()

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
            tempList.clear()

            tempList.add(triceps)
            exercicesList.add(Exercice("Extension des triceps à la machine", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension des triceps à la poulie à un bras", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension des triceps à la poulie avec corde", "", tempList, tempListSecond))
            tempListSecond.add(pectoraux)
            exercicesList.add(Exercice("Extension des triceps à la poulie coudes écartés", "", tempList, tempListSecond))
            tempListSecond.clear()
            exercicesList.add(Exercice("Extension des triceps à la poulie en pronation", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension des triceps à la poulie en supination", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension des triceps à la poulie haute à genoux", "", tempList, tempListSecond))
            tempListSecond.add(pectoraux); tempListSecond.add(dorsaux); tempListSecond.add(epaules)
            exercicesList.add(Exercice("Extension des triceps bras à 180 degrés", "", tempList, tempListSecond))
            tempListSecond.clear()
            exercicesList.add(Exercice("Extension des triceps buste penché à la poulie haute", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension des triceps contre un mur", "", tempList, tempListSecond))
            tempList.clear()

            tempList.add(ischio); tempList.add(fessiers)
            tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Extension inversé", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(triceps)
            tempListSecond.add(epaules)
            exercicesList.add(Exercice("Extension nuque", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension nuque à la poulie", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Extension nuque à un bras", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(fessiers); tempList.add(quadriceps)
            tempListSecond.add(ischio); tempListSecond.add(mollets); tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Fente à la barre", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Fente à la smith machine", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Fente avec pied sur banc", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Fente en marchant à la barre", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Fente en reculant à la barre", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Fente glissée", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(ischio)
            tempListSecond.add(ischio); tempListSecond.add(mollets); tempListSecond.add(lombaires); tempListSecond.add(quadriceps); tempListSecond.add(abdominaux); tempListSecond.add(fessiers)
            exercicesList.add(Exercice("Fente latérale", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(quadriceps)
            tempListSecond.add(abdominaux)
            exercicesList.add(Exercice("Flexion de la hanche à une jambe à la machine", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(abdominaux)
            tempListSecond.add(dorsaux)
            exercicesList.add(Exercice("Flexion latérale avec haltère", "", tempList, tempListSecond))
            tempListSecond.clear()

            tempList.add(lombaires)
            exercicesList.add(Exercice("Gainage abdominal", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Gainage oblique", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(ischio)
            tempListSecond.add(mollets); tempListSecond.add(abdominaux); tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Glute ham raise", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(quadriceps); tempList.add(fessiers)
            tempListSecond.add(ischio); tempListSecond.add(mollets); tempListSecond.add(lombaires); tempListSecond.add(abdominaux)
            exercicesList.add(Exercice("Gobelet squat avec haltère", "", tempList, tempListSecond))
            tempListSecond.clear()
            tempListSecond.add(ischio); tempList.add(mollets)
            exercicesList.add(Exercice("Hack squat à la machine", "", tempList, tempListSecond))
            tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Montée sur banc", "", tempList, tempListSecond))
            tempListSecond.add(avantbras); tempListSecond.add(trapezes)
            exercicesList.add(Exercice("Hack squat avec une barre", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(ischio); tempList.add(fessiers); tempList.add(lombaires)
            tempListSecond.add(mollets)
            exercicesList.add(Exercice("Good Morning", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(triceps)
            tempListSecond.add(epaules)
            exercicesList.add(Exercice("Kickback", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Kickback à la poulie", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(ischio)
            tempListSecond.add(mollets)
            exercicesList.add(Exercice("Leg curl allongé", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Leg curl debout à une jambe", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Leg curl debout à une jambe à la poulie", "", tempList, tempListSecond))
            tempListSecond.clear()
            tempList.add(mollets)
            exercicesList.add(Exercice("Leg curl assis", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(quadriceps)
            exercicesList.add(Exercice("Leg extension allongé", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Leg extension assis", "", tempList, tempListSecond))
            tempList.clear()

            tempList.add(mollets)
            exercicesList.add(Exercice("Mollets à la presse à cuisses", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Mollets assis", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Mollets assis jambes tendues", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Mollets debout à la machine", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Mollets debout à une jambe", "", tempList, tempListSecond))
            tempList.clear()

            tempList.add(abdominaux)
            tempListSecond.add(dorsaux)
            exercicesList.add(Exercice("Obliques avec l'abmat", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Obliques sur banc à lombaires", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Obliques sur la swiss ball", "", tempList, tempListSecond))
            tempListSecond.add(fessiers)
            exercicesList.add(Exercice("Obliques suspendu à la barre fixe", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(epaules); tempList.add(dorsaux); tempList.add(trapezes)
            tempListSecond.add(biceps); tempListSecond.add(triceps); tempListSecond.add(avantbras); tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Oiseau / rowing avec haltères", "", tempList, tempListSecond))
            tempList.clear()
            tempListSecond.clear()

            tempList.add(epaules); tempList.add(dorsaux); tempList.add(triceps)
            tempListSecond.add(trapezes); tempListSecond.add(avantbras)
            exercicesList.add(Exercice("Oiseau à la machine", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Oiseau à la poulie haute", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Oiseau à un bras allongé", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Oiseau sur banc incliné", "", tempList, tempListSecond))
            tempListSecond.add(lombaires)
            exercicesList.add(Exercice("Oiseau à la poulie basse", "", tempList, tempListSecond))
            exercicesList.add(Exercice("Oiseau avec haltères", "", tempList, tempListSecond))
        }
    }
}