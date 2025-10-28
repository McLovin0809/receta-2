package com.example.receta_2.data.model

import com.example.receta_2.R

val allCategories: List<SearchCategory> = listOf(
    // --- GRUPO: TIPO DE PLATO ---
    SearchCategory("entradas", "Entradas", R.drawable.entradas, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("platos_principales", "Platos principales", R.drawable.platosprincipales, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("guarniciones", "Guarniciones", R.drawable.guarniciones, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("postres", "Postres", R.drawable.postres, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("sopas_y_cremas", "Sopas y cremas", R.drawable.sopasycremas, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("ensaladas", "Ensaladas", R.drawable.ensaladas, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("salsas_y_aderezos", "Salsas y aderezos", R.drawable.salsasyaderezos, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("bocadillos", "Bocadillos", R.drawable.bocadillos, 4, CategoryGroup.MEAL_TYPE),
    SearchCategory("tapas", "Tapas", R.drawable.tapas, 4, CategoryGroup.MEAL_TYPE),

    // --- GRUPO: MOMENTO DEL DÍA ---
    SearchCategory("desayuno", "Desayuno", R.drawable.desayuno, 2, CategoryGroup.TIME_OF_DAY),
    SearchCategory("almuerzo", "Almuerzo", R.drawable.almuerzo, 3, CategoryGroup.TIME_OF_DAY),
    SearchCategory("cena", "Cena", R.drawable.cena, 3, CategoryGroup.TIME_OF_DAY),
    SearchCategory("merienda", "Merienda", R.drawable.merienda, 2, CategoryGroup.TIME_OF_DAY),

    // --- GRUPO: ESTILO DE COCINA ---
    SearchCategory("mediterranea", "Mediterránea", R.drawable.mediterranea, 2, CategoryGroup.CUISINE_STYLE),
    SearchCategory("asiatica", "Asiática", R.drawable.asiatica, 1, CategoryGroup.CUISINE_STYLE),
    SearchCategory("mexicana", "Mexicana", R.drawable.mexicana, 1, CategoryGroup.CUISINE_STYLE),
    SearchCategory("italiana", "Italiana", R.drawable.italiana, 2, CategoryGroup.CUISINE_STYLE),
    SearchCategory("chilena", "Chilena", R.drawable.chilena, 1, CategoryGroup.CUISINE_STYLE),

    // --- GRUPO: NECESIDAD DIETÉTICA ---
    SearchCategory("vegetarianas", "Vegetarianas", R.drawable.vegetarianas, 2, CategoryGroup.DIETARY_NEED),
    SearchCategory("veganas", "Veganas", R.drawable.veganas, 1, CategoryGroup.DIETARY_NEED),
    SearchCategory("sin_gluten", "Sin gluten", R.drawable.ic_launcher_background, 1, CategoryGroup.DIETARY_NEED),
    SearchCategory("bajo_en_calorias", "Bajo en calorías", R.drawable.ic_launcher_background, 2, CategoryGroup.DIETARY_NEED),

    // --- GRUPO: MÉTODO DE COCCIÓN ---
    SearchCategory("horneados", "Horneados", R.drawable.ic_launcher_background, 1, CategoryGroup.COOKING_METHOD),
    SearchCategory("a_la_parrilla", "A la parrilla", R.drawable.ic_launcher_background, 1, CategoryGroup.COOKING_METHOD),
    SearchCategory("fritos", "Fritos", R.drawable.ic_launcher_background, 0, CategoryGroup.COOKING_METHOD),
    SearchCategory("sin_coccion", "Sin cocción", R.drawable.ic_launcher_background, 2, CategoryGroup.COOKING_METHOD),

    // --- GRUPO: OCASIÓN ESPECIAL ---
    SearchCategory("navidad", "Navidad", R.drawable.ic_launcher_background, 0, CategoryGroup.SPECIAL_OCCASION),
    SearchCategory("verano", "Verano", R.drawable.ic_launcher_background, 2, CategoryGroup.SPECIAL_OCCASION),
    SearchCategory("fiestas", "Fiestas", R.drawable.ic_launcher_background, 0, CategoryGroup.SPECIAL_OCCASION),

    // --- GRUPO: DIFICULTAD ---
    SearchCategory("para_principiantes", "Para principiantes", R.drawable.ic_launcher_foreground, 3, CategoryGroup.DIFFICULTY),
    SearchCategory("nivel_intermedio", "Nivel intermedio", R.drawable.ic_launcher_background, 1, CategoryGroup.DIFFICULTY),
    SearchCategory("avanzado", "Avanzado", R.drawable.ic_launcher_foreground, 0, CategoryGroup.DIFFICULTY)
)

val sampleRecipes: List<Recipe> = listOf(
    // --- RECETAS DE ENTRADAS ---
    Recipe("entrada_01", "Bruschetta de Tomate", "Clásica entrada italiana, fresca y llena de sabor.", R.drawable.ic_launcher_background,
        listOf("entradas", "sin_coccion", "para_principiantes", "mediterranea", "vegetarianas", "verano", "italiana"),
        listOf("4 rebanadas de pan rústico", "2 tomates maduros", "1 diente de ajo", "Hojas de albahaca fresca", "Aceite de oliva virgen extra", "Sal y pimienta al gusto"),
        listOf("Tostar las rebanadas de pan.", "Frotar suavemente el diente de ajo sobre el pan tostado.", "Picar los tomates y la albahaca, y mezclarlos en un bol con aceite de oliva, sal y pimienta.", "Colocar la mezcla de tomate sobre el pan.")
    ),
    Recipe("entrada_02", "Ceviche Clásico", "Pescado marinado en limón, un plato refrescante y ligero.", R.drawable.ic_launcher_background,
        listOf("entradas", "pescados", "chilena", "sin_coccion", "bajo_en_calorias", "verano"),
        listOf("500g de pescado blanco firme (corvina o reineta)", "1 taza de jugo de limón fresco", "1 cebolla morada en juliana", "1 ají (chile) sin venas picado", "Hojas de cilantro fresco picado", "Sal y pimienta"),
        listOf("Cortar el pescado en cubos de 1-2 cm.", "Colocar el pescado en un bol de vidrio y cubrir con el jugo de limón. Refrigerar por 20 minutos o hasta que el pescado esté opaco.", "Añadir la cebolla, ají, cilantro, sal y pimienta. Mezclar bien.", "Servir frío, acompañado de camote o maíz cancha si se desea.")
    ),

    // --- RECETAS DE PLATOS PRINCIPALES ---
    Recipe("principal_01", "Lasaña a la Boloñesa", "Un clásico de la cocina italiana que nunca falla, perfecto para una comida familiar.", R.drawable.ic_launcher_background,
        listOf("platos_principales", "italiana", "horneados", "almuerzo", "cena", "nivel_intermedio"),
        listOf("12 láminas de lasaña", "500g de carne molida", "1 cebolla", "2 dientes de ajo", "800g de tomate triturado", "50g de mantequilla", "50g de harina", "1 litro de leche (para bechamel)", "Queso parmesano rallado", "Nuez moscada, sal y pimienta"),
        listOf("Sofreír la cebolla y el ajo picados. Añadir la carne y cocinar hasta que esté dorada.", "Incorporar el tomate triturado, sal, pimienta y cocinar a fuego lento por 30 minutos (salsa boloñesa).", "Para la bechamel: derretir la mantequilla, añadir la harina y cocinar por 1 minuto. Verter la leche poco a poco sin dejar de remover hasta que espese. Sazonar con sal, pimienta y nuez moscada.", "Montar la lasaña en una fuente para horno: capa de bechamel, láminas de pasta, boloñesa, bechamel y queso parmesano. Repetir.", "Terminar con una capa de bechamel y abundante queso parmesano.", "Hornear a 180°C durante 30-40 minutos, o hasta que esté dorada y burbujeante.")
    ),

    // --- RECETAS DE POSTRES ---
    Recipe("postre_01", "Tarta de Manzana Casera", "Una tarta rústica y deliciosa con un toque de canela.", R.drawable.ic_launcher_background,
        listOf("postres", "horneados", "merienda", "vegetarianas"),
        listOf("1 lámina de masa quebrada o de hojaldre", "4 manzanas grandes (tipo Golden o Reineta)", "100g de azúcar", "1 cucharadita de canela en polvo", "Jugo de medio limón", "Mermelada de albaricoque para abrillantar (opcional)"),
        listOf("Precalentar el horno a 190°C.", "Pelar, descorazonar y cortar las manzanas en láminas finas. Rociarlas con el jugo de limón para que no se oxiden.", "Forrar un molde para tarta con la masa. Pinchar el fondo con un tenedor.", "Colocar las láminas de manzana de forma decorativa sobre la masa.", "Mezclar el azúcar con la canela y espolvorear sobre las manzanas.", "Hornear durante 35-45 minutos, hasta que la masa esté dorada y las manzanas tiernas.", "Si se desea, calentar un poco de mermelada con agua y pincelar la tarta para darle brillo.")
    ),

    // --- RECETAS DE DESAYUNO ---
    Recipe("desayuno_01", "Tostadas con Aguacate y Huevo", "Un desayuno completo, nutritivo y muy rápido de preparar.", R.drawable.ic_launcher_background,
        listOf("desayuno", "para_principiantes", "vegetariano", "bajo_en_calorias"),
        listOf("2 rebanadas de pan integral", "1 aguacate maduro", "2 huevos", "Sal, pimienta y un chorrito de aceite de oliva", "Copos de chile (opcional)"),
        listOf("Tostar las rebanadas de pan.", "Mientras tanto, cocinar los huevos a la plancha o poché.", "Machacar el aguacate con un tenedor y sazonar con sal y pimienta. Untarlo sobre las tostadas.", "Colocar un huevo sobre cada tostada. Añadir un chorrito de aceite de oliva, más sal, pimienta y copos de chile si se gusta.")
    )
)
