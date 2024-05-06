
import shared
import UIKit
import SwiftUI
import Kingfisher

struct KingfisherView: View {
    private let url: String
    private let onSuccess: ((RetrieveImageResult) -> Void)?
    private let onFailure: ((KingfisherError) -> Void)?
    
    init(
        url: String,
        onSuccess: ((RetrieveImageResult) -> Void)? = nil,
        onFailure: ((KingfisherError) -> Void)? = nil
    ) {
        self.url = url
        self.onSuccess = onSuccess
        self.onFailure = onFailure
    }
    
    var body: some View {
        if #available(iOS 14.0, *) {
            KFImage(URL(string: url))
                .onSuccess(onSuccess)
                .onFailure(onFailure)
                .resizable()
                .aspectRatio(contentMode: .fit)
        } else {
            KingfisherUIKitView(
                url: url,
                onSuccess: onSuccess,
                onFailure: onFailure
            ).aspectRatio(contentMode: .fit)
        }
    }
}

private struct KingfisherUIKitView: UIViewRepresentable {
    typealias UIViewType = UIImageView
    
    private let url: String
    private let onSuccess: ((RetrieveImageResult) -> Void)?
    private let onFailure: ((KingfisherError) -> Void)?
    
    init(
        url: String,
        onSuccess: ((RetrieveImageResult) -> Void)? = nil,
        onFailure: ((KingfisherError) -> Void)? = nil
    ) {
        self.url = url
        self.onSuccess = onSuccess
        self.onFailure = onFailure
    }
    
    func makeUIView(context: Context) -> UIImageView {
        return UIImageView()
    }
    
    func updateUIView(_ uiView: UIImageView, context: Context) {
        uiView.kf.setImage(with: URL(string: url)) { result in
            switch result {
            case .success(let result):
                onSuccess?(result)
            case .failure(let error):
                onFailure?(error)
            }
        }
    }
}
