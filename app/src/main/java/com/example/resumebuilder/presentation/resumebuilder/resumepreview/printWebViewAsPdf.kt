package com.example.resumebuilder.presentation.resumebuilder.resumepreview

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView

// Ye function Android ke built-in Print Framework ko trigger karta hai.
// PrintManager.print() call karte hi system apna "Save as PDF" dialog khud dikhata hai —
// user wahan se file ka naam edit kar sakta hai aur location (Downloads, Drive, etc.) choose kar sakta hai.
// Humein khud LayoutResultCallback/WriteResultCallback banane ki zaroorat nahi —
// system yehi cheezein internally handle karta hai.
fun printWebViewAsPdf(
    context: Context,
    webView: WebView,
    jobName: String
) {
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager

    // WebView khud apna PrintDocumentAdapter deta hai jo current loaded content ko print-ready banata hai
    val printAdapter = webView.createPrintDocumentAdapter(jobName)

    val printAttributes = PrintAttributes.Builder()
        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        .setResolution(PrintAttributes.Resolution("pdf", "pdf", 300, 300))
        .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
        .build()

    // Ye line system dialog open karti hai — "jobName" hi default file name ki tarah dikhega dialog mein
    printManager.print(jobName, printAdapter, printAttributes)
}