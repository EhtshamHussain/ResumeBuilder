package com.example.resumebuilder.presentation.resumebuilder.resumepreview

sealed class ResumePreviewEvent {
    //    data object ScreenEntered : ResumePreviewEvent()
    data object ChangeTemplateClicked : ResumePreviewEvent()
    data object DownloadPdfClicked : ResumePreviewEvent()
    /***
     * A WebView cannot generate a PDF on its own. Android's PrintManager and PdfDocument APIs require a View object,
    which is only accessible within the UI layer (Composable) and not the ViewModel.
    Therefore, the actual PDF generation must happen in the UI,
    with the ViewModel only receiving the result.

     * [ WebView (Page Load) ] ---> [ PrintDocumentAdapter ] ---> [ PrintManager (Android OS) ] ---> [ Save PDF ]

     */
}