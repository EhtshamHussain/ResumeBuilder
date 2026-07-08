package com.example.resumebuilder.presentation.resumebuilder.resumepreview

sealed class ResumePreviewEvent {
    data object ScreenEntered : ResumePreviewEvent()
    data object ChangeTemplateClicked : ResumePreviewEvent()
    data object DownloadPdfClicked : ResumePreviewEvent()

    // WebView khud PDF generate nahi kar sakta — Android ka PrintManager/PdfDocument API
    // View object maangta hai, jo sirf Composable ke paas hota hai (ViewModel ke paas nahi).
    // Isliye actual PDF generation UI layer (Screen) mein hoga, aur ViewModel ko sirf result batayega.
//    data class PdfSaved(val filePath: String) : ResumePreviewEvent()
//    data class PdfSaveFailed(val message: String) : ResumePreviewEvent()
}