
protocol ThemeProvider: AnyObject {
    var theme: any Theme { get }
    
    func register<Observer: Themeable>(observer: Observer)
    func setTheme(_ theme: any Theme)
}
