//
//  TimeTravelClientViewController.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 18.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

class TimeTravelClientViewController: UIViewController {
    private let tableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        return tableView
    }()
    
    private let timeTravelButtons = {
        let stack = UIStackView()
        stack.axis = .horizontal
        stack.spacing = 16.0
        stack.alignment = .center
        stack.distribution = .fillEqually
        stack.translatesAutoresizingMaskIntoConstraints = false
        return stack
    }()
    
    private lazy var stopButton = createButton(
        imageName: "stop_recording",
        clickAction: #selector(onStopRecordingClick)
    )
    private lazy var cancelButton = createButton(
        imageName: "close",
        clickAction: #selector(onCancelClick)
    )
    private lazy var recordButton = createButton(
        imageName: "start_recording",
        clickAction: #selector(onStartRecordingClick)
    )
    private lazy var moveToEndButton = createButton(
        imageName: "move_to_end",
        clickAction: #selector(onMoveToEndClick)
    )
    private lazy var moveToStartButton = createButton(
        imageName: "move_to_start",
        clickAction: #selector(onMoveToStartClick)
    )
    private lazy var stepForwardButton = createButton(
        imageName: "arrow_right",
        clickAction: #selector(self.onStepForwardClick)
    )
    private lazy var stepBackwardButton = createButton(
        imageName: "arrow_left",
        clickAction: #selector(onStepBackwardClick)
    )
    
    private var state: TimeTravelState?
    private var disposable: Disposable?
    
    private let timeTravelController = TimeTravelControllerProviderKt.timeTravelController
    
    private let component: TimeTravelClientComponent
    
    init(component: TimeTravelClientComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        themeProvider.register(observer: self)
        setupButtons()
        setupTableView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        disposable = timeTravelController.states(observer: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        disposable?.dispose()
        disposable = nil
        state = nil
        super.viewDidDisappear(animated)
        if isBeingDismissed {
            component.navigateBack()
        }
    }
    
    private func updateButtons(with mode: TimeTravelState.Mode) {
        switch mode {
        case .idle:
            recordButton.isHidden = false
            stopButton.isHidden = true
            moveToStartButton.isHidden = true
            stepBackwardButton.isHidden = true
            stepForwardButton.isHidden = true
            moveToEndButton.isHidden = true
            cancelButton.isHidden = true
        case .recording:
            recordButton.isHidden = true
            stopButton.isHidden = false
            moveToStartButton.isHidden = true
            stepBackwardButton.isHidden = true
            stepForwardButton.isHidden = true
            moveToEndButton.isHidden = true
            cancelButton.isHidden = false
        case .stopped:
            recordButton.isHidden = true
            stopButton.isHidden = true
            moveToStartButton.isHidden = false
            stepBackwardButton.isHidden = false
            stepForwardButton.isHidden = false
            moveToEndButton.isHidden = false
            cancelButton.isHidden = false
        default:
            break
        }
    }
    
    private func setupTableView() {
        self.view.addSubview(tableView)
        tableView.register(TimeTravelEventCell.self, forCellReuseIdentifier: "TimeTravelEvent")
        tableView.delegate = self
        tableView.dataSource = self
        tableView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        tableView.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        tableView.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: timeTravelButtons.topAnchor, constant: -8).isActive = true
    }
    
    private func setupButtons() {
        view.addSubview(timeTravelButtons)
        timeTravelButtons.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        timeTravelButtons.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        timeTravelButtons.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        timeTravelButtons.addArrangedSubviews(
            recordButton,
            stopButton,
            moveToStartButton,
            stepBackwardButton,
            stepForwardButton,
            moveToEndButton,
            cancelButton
        )
    }
    
    @objc
    private func onStartRecordingClick() {
        timeTravelController.startRecording()
    }
    
    @objc
    private func onStopRecordingClick() {
        timeTravelController.stopRecording()
    }
    
    @objc
    private func onMoveToStartClick() {
        timeTravelController.moveToStart()
    }
    
    @objc
    private func onStepBackwardClick() {
        timeTravelController.stepBackward()
    }
    
    @objc
    private func onStepForwardClick() {
        timeTravelController.stepForward()
    }
    
    @objc
    private func onMoveToEndClick() {
        timeTravelController.moveToEnd()
    }
    
    @objc
    private func onCancelClick() {
        timeTravelController.cancel()
    }
    
    private func createButton(imageName: String, clickAction: Selector) -> UIButton {
        let button = TimeTravelButton(imageName: imageName)
        button.addTarget(self, action: clickAction, for: .touchUpInside)
        return button
    }
}


extension TimeTravelClientViewController: Themeable {
    func apply(theme: any Theme) {
        view.backgroundColor = theme.colors.background
        tableView.backgroundColor = theme.colors.background
    }
}

extension TimeTravelClientViewController: Observer {
    func onNext(value: Any?) {
        if let state = value as? TimeTravelState {
            self.state = state
            updateButtons(with: state.mode)
            tableView.reloadData()
        }
    }
    
    func onComplete() { }
}

extension TimeTravelClientViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let state = self.state else { return }
        
        let event = state.events[indexPath.row]
        let eventData = event.value as AnyObject
        
        let alert = UIAlertController(
            title: event.storeName,
            message: eventData.description ?? "",
            preferredStyle: .alert
        )
        alert.addAction(UIAlertAction(title: "Close", style: .cancel, handler: nil))
        
        present(alert, animated: true)
    }
}

extension TimeTravelClientViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return state?.events.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TimeTravelEvent") as! TimeTravelEventCell
        guard let state = self.state else { return cell }
        let event = state.events[indexPath.row]
        cell.bind(with: event, selected: state.selectedEventIndex == indexPath.row)
        cell.onDebugClick = { [unowned self] eventId in
            self.timeTravelController.debugEvent(eventId: eventId)
        }
        return cell
    }
}
