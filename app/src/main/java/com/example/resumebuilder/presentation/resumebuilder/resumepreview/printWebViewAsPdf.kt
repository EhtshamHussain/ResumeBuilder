package com.example.resumebuilder.presentation.resumebuilder.resumepreview

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView

fun printWebViewAsPdf(
    context: Context,
    webView: WebView,
    jobName: String
) {
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
    val printAdapter = webView.createPrintDocumentAdapter(jobName)
    val printAttributes = PrintAttributes.Builder()
        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        .setResolution(PrintAttributes.Resolution("pdf", "pdf", 300, 300))
        .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
        .build()
    printManager.print(jobName, printAdapter, printAttributes)
}
