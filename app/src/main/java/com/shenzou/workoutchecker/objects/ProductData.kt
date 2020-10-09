package com.shenzou.workoutchecker.objects

import android.graphics.Bitmap
import java.io.Serializable

class ProductData(
    val code: String,
    val product: Product
): Serializable

class Product(
    val product_name_fr: String,
    val nutriments: NutrimentsData,
    val image_front_small_url: String
): Serializable

class NutrimentsData(): Serializable{
    //for 100g
    val energy: Double? = null
    val proteins: Double? = null
    val carbohydrates: Double? = null
    val sugars: Double? = null
    val glucose: Double? = null
    val fructose: Double? = null
    val lactose: Double? = null
    val maltose: Double? = null
    val maltodextrins: Double? = null
    val starch: Double? = null
    val polyols: Double? = null
    val fat: Double? = null
    val saturated_fat: Double? = null
    val butyric_acid: Double? = null
    val caproic_acid: Double? = null
    val caprylic_acid: Double? = null
    val capric_acid: Double? = null
    val lauric_acid: Double? = null
    val myristic_acid: Double? = null
    val palmitic_acid: Double? = null
    val stearic_acid: Double? = null
    val arachidic_acid: Double? = null
    val behenic_acid: Double? = null
    val lignoceric_acid: Double? = null
    val cerotic_acid: Double? = null
    val montanic_acid: Double? = null
    val melissic_acid: Double? = null
    val monounsaturated_fat: Double? = null
    val polyunsaturated_fat: Double? = null
    val omega_3_fat: Double? = null
    val alpha_linolenic_acid: Double? = null
    val eicosapentaenoic_acid: Double? = null
    val docosahexaenoic_acid: Double? = null
    val omega_6_fat: Double? = null
    val linoleic_acid: Double? = null
    val arachidonic_acid: Double? = null
    val gamma_linolenic_acid: Double? = null
    val dihomo_gamma_linolenic_acid: Double? = null
    val omega_9_fat: Double? = null
    val oleic_acid: Double? = null
    val elaidic_acid: Double? = null
    val gondoic_acid: Double? = null
    val mead_acid: Double? = null
    val erucic_acid: Double? = null
    val nervonic_acid: Double? = null
    val trans_fat: Double? = null
    val cholesterol: Double? = null
    val fiber: Double? = null
    val sodium: Double? = null
    val alcohol: Double? = null
    val vitamin_a: Double? = null
    val vitamin_d: Double? = null
    val vitamin_e: Double? = null
    val vitamin_k: Double? = null
    val vitamin_c: Double? = null
    val vitamin_b1: Double? = null
    val vitamin_b2: Double? = null
    val vitamin_pp: Double? = null
    val vitamin_b6: Double? = null
    val vitamin_b9: Double? = null
    val vitamin_b12: Double? = null
    val biotin: Double? = null
    val pantothenic_acid: Double? = null
    val silica: Double? = null
    val bicarbonate: Double? = null
    val potassium: Double? = null
    val chloride: Double? = null
    val calcium: Double? = null
    val phosphorus: Double? = null
    val iron: Double? = null
    val magnesium: Double? = null
    val zinc: Double? = null
    val copper: Double? = null
    val manganese: Double? = null
    val fluoride: Double? = null
    val selenium: Double? = null
    val chromium: Double? = null
    val molybdenum: Double? = null
    val iodine: Double? = null
    val caffeine: Double? = null
    val taurine: Double? = null
}
