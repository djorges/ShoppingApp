import Foundation
import ComposeApp


private var _koin: Koin_coreKoin? = nil

var koin:Koin_coreKoin{
    return _koin!
}

func startKoin(){
    let koinApplication = MainModuleKt.doInitKoinIOS()
    _koin = koinApplication.koin
}