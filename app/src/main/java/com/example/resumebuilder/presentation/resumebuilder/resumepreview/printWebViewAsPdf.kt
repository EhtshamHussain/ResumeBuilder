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
    /***    loadDataWithBaseURL show HTML and createPrintDocumentAdapter print-ready package for printing or generating PDF
     * A WebView cannot generate a PDF on its own. Android's PrintManager and PdfDocument APIs require a View object,
    which is only accessible within the UI layer (Composable) and not the ViewModel.
    Therefore, the actual PDF generation must happen in the UI,
    with the ViewModel only receiving the result.

     * [ WebView (Page Load) with loadDataWithBaseURL] ---> [ PrintDocumentAdapter ] ---> [ PrintManager (Android OS) ] ---> [ Save PDF ]*/

    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
    val printAdapter = webView.createPrintDocumentAdapter(jobName)
    val printAttributes = PrintAttributes.Builder()
        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        .setResolution(PrintAttributes.Resolution("pdf", "pdf", 300, 300))
        .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
        .build()
    printManager.print(jobName, printAdapter, printAttributes)
}
