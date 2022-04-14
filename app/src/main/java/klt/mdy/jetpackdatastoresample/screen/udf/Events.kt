package klt.mdy.jetpackdatastoresample.screen.udf

sealed class Events {
    object OpenSheet : Events()
    object CloseSheet : Events()

}
