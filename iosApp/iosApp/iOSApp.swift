import SwiftUI

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate:AppDelegate

    var rootHolder: RootHolder{
        appDelegate.getRootHolder()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

class RootHolder: ObservableObject{
    init(){
        lifecycle = koin.lifecycleRegistry
        root = koin.rootComponent

        LifecycleRegistryExtKt.create(lifecycle)
    }
    deinit{
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}

class AppDelegate : NSObject, UIApplicationDelegate {
    var rootHolder: RootHolder? = nil

    func getRootHolder() -> RootHolder{
        if(rootHolder == nil){
            rootHolder = RootHolder()
        }
        return rootHolder!
    }

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool{
        startKoin()
        rootHolder = RootHolder()

        return true
    }
}