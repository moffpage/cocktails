
protocol Themeable: AnyObject {
    func apply(theme: any Theme)
}

extension Themeable {
    var themeProvider: ThemeProvider {
        return ThemeProviderCompat.shared
    }
}
