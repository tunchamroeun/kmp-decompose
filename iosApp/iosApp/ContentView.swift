import ComposeApp
import SwiftUI
import UIKit

struct ComposeView: UIViewControllerRepresentable {
    let root: RootComponent
    let backDispatcher: BackDispatcher
    func makeUIViewController(context _: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(root: root, backDispatcher: backDispatcher)
    }

    func updateUIViewController(_: UIViewController, context _: Context) {}
}

struct ContentView: View {
    let sharedViewModel = SharedViewModel.shared

    @StateValue
    private var sharedViewModelState: SharedViewModel.State

    private let counter = Counter.shared

    @StateValue
    private var state: Counter.State

    let root: RootComponent
    let backDispatcher: BackDispatcher

    init(root: RootComponent, backDispatcher: BackDispatcher) {
        _state = StateValue(counter.state)
        _sharedViewModelState = StateValue(sharedViewModel.state)
        self.root = root
        self.backDispatcher = backDispatcher
    }

    var body: some View {
        VStack {
            Button("Increase : \(state.count)") {
                sharedViewModel.changeFirstName(text: "I changed you from native")
                counter.increment()
            }

            Text(sharedViewModelState.firstName)

            ComposeView(root: root, backDispatcher: backDispatcher)
                .ignoresSafeArea(.all)
                .ignoresSafeArea(.keyboard)
        }
    }
}
