package com.lokodom.todaymeal.viewmodel.translate

import android.content.Context
import android.widget.Toast
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.lokodom.todaymeal.viewmodel.MealViewModel


fun MealViewModel.translation(text: List<String>, context: Context) {

    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()

    val languageTranslator = Translation.getClient(options)

    for ((index, item) in text.withIndex()) {
        languageTranslator.translate(item)
            .addOnSuccessListener { translatedText ->
                when (index) {
                    0 -> _state.value = _state.value.copy(translatedName = translatedText)
                    1 -> _state.value = _state.value.copy(translatedCategory = translatedText)
                    2 -> _state.value = _state.value.copy(translatedCountry = translatedText)
                    3 -> _state.value = _state.value.copy(translatedIng1 = translatedText)
                    4 -> _state.value = _state.value.copy(translatedIng2 = translatedText)
                    5 -> _state.value = _state.value.copy(translatedIng3 = translatedText)
                    6 -> _state.value = _state.value.copy(translatedIng4 = translatedText)
                    7 -> _state.value = _state.value.copy(translatedIng5 = translatedText)
                    8 -> _state.value = _state.value.copy(translatedIng6 = translatedText)
                    9 -> _state.value = _state.value.copy(translatedIng7 = translatedText)
                    10 -> _state.value = _state.value.copy(translatedIng8 = translatedText)
                    11 -> _state.value = _state.value.copy(translatedIng9 = translatedText)
                    12 -> _state.value = _state.value.copy(translatedIng10 = translatedText)
                    13 -> _state.value = _state.value.copy(translatedIng11 = translatedText)
                    14 -> _state.value = _state.value.copy(translatedIng12 = translatedText)
                    15 -> _state.value = _state.value.copy(translatedIng13 = translatedText)
                    16 -> _state.value = _state.value.copy(translatedIng14 = translatedText)
                    17 -> _state.value = _state.value.copy(translatedIng15 = translatedText)

                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "descargando...",
                    Toast.LENGTH_SHORT
                ).show()
                downloadModelIfNecessary(languageTranslator, context)
            }
    }
}

fun MealViewModel.translationGreek(text: List<String>, context: Context) {

    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.GREEK)
        .build()

    val languageTranslator = Translation.getClient(options)

    for ((index, item) in text.withIndex()) {
        languageTranslator.translate(item)
            .addOnSuccessListener { translatedText ->
                when (index) {
                    0 -> _state.value = _state.value.copy(translatedName = translatedText)
                    1 -> _state.value = _state.value.copy(translatedCategory = translatedText)
                    2 -> _state.value = _state.value.copy(translatedCountry = translatedText)
                    3 -> _state.value = _state.value.copy(translatedIng1 = translatedText)
                    4 -> _state.value = _state.value.copy(translatedIng2 = translatedText)
                    5 -> _state.value = _state.value.copy(translatedIng3 = translatedText)
                    6 -> _state.value = _state.value.copy(translatedIng4 = translatedText)
                    7 -> _state.value = _state.value.copy(translatedIng5 = translatedText)
                    8 -> _state.value = _state.value.copy(translatedIng6 = translatedText)
                    9 -> _state.value = _state.value.copy(translatedIng7 = translatedText)
                    10 -> _state.value = _state.value.copy(translatedIng8 = translatedText)
                    11 -> _state.value = _state.value.copy(translatedIng9 = translatedText)
                    12 -> _state.value = _state.value.copy(translatedIng10 = translatedText)
                    13 -> _state.value = _state.value.copy(translatedIng11 = translatedText)
                    14 -> _state.value = _state.value.copy(translatedIng12 = translatedText)
                    15 -> _state.value = _state.value.copy(translatedIng13 = translatedText)
                    16 -> _state.value = _state.value.copy(translatedIng14 = translatedText)
                    17 -> _state.value = _state.value.copy(translatedIng15 = translatedText)

                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "descargando...",
                    Toast.LENGTH_SHORT
                ).show()
                downloadModelIfNecessary(languageTranslator, context)
            }
    }
}
private fun MealViewModel.downloadModelIfNecessary(
    languageTranslator: Translator,
    context: Context
){

    _state.value = _state.value.copy(buttonEnabled = false)

    val conditions = DownloadConditions
        .Builder()
        .requireWifi()
        .build()

    languageTranslator
        .downloadModelIfNeeded(conditions)
        .addOnSuccessListener {
            Toast.makeText(context,
                "descarga correcta",
                Toast.LENGTH_SHORT).show()
            _state.value = _state.value.copy(buttonEnabled = true)
        }
        .addOnFailureListener {
            Toast.makeText(context,
                "no se descargo el modelo",
                Toast.LENGTH_SHORT).show()
        }
}