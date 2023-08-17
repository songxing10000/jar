import Cocoa

struct MapData {
    var width: Int = 0
    var height: Int = 0
    var datas: [Int] = []
}

class ViewController: NSViewController {
    
    var mapDatas: [MapData] = []
    var mapImgs:[NSImage] = []
     @IBOutlet weak var m_stv: NSStackView!
    var m_showIdx: Int = 0
    
    @IBAction func clickPreBtn(_ sender: Any) {
        if m_showIdx >= 1 {
            m_showIdx = m_showIdx - 1
            
            m_stv.subviews.forEach {  $0.removeFromSuperview()}
            m_stv.addArrangedSubview(NSImageView(image: mapImgs[m_showIdx]))
        }
    }
    @IBAction func clickNextBtn(_ sender: Any) {
        if m_showIdx + 1 < mapDatas.count {
            m_showIdx = m_showIdx + 1
            
            m_stv.subviews.forEach {  $0.removeFromSuperview()}
            m_stv.addArrangedSubview(NSImageView(image: mapImgs[m_showIdx]))
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadMapData()
 
        for mapData in mapDatas {
            let img = loadMapImg(mapData: mapData)
            mapImgs.append(img)
        }
        m_stv.subviews.forEach {  $0.removeFromSuperview()}
        m_stv.addArrangedSubview(NSImageView(image: mapImgs[0]))
    }
    func loadMapImg(mapData: MapData) -> NSImage {
        // 定义小图的大小和数量
        let smallImageSize = CGSize(width: 24, height: 24)
        
        // 定义大图的大小
        let bigImageSize = CGSize(width: smallImageSize.width * CGFloat(mapData.width), height: smallImageSize.height * CGFloat(mapData.height))
        
        // 创建大图的上下文
        let bitmapRep = NSBitmapImageRep(bitmapDataPlanes: nil,
                                         pixelsWide: Int(bigImageSize.width),
                                         pixelsHigh: Int(bigImageSize.height),
                                         bitsPerSample: 8,
                                         samplesPerPixel: 4,
                                         hasAlpha: true,
                                         isPlanar: false,
                                         colorSpaceName: NSColorSpaceName.deviceRGB,
                                         bytesPerRow: 0,
                                         bitsPerPixel: 0)
        
        guard let context = NSGraphicsContext(bitmapImageRep: bitmapRep!)?.cgContext else {
            fatalError("无法创建图形上下文")
        }
        
        // 设置当前图形上下文
        NSGraphicsContext.current = NSGraphicsContext(cgContext: context, flipped: false)
        // 这里要倒序
        for j in 0..<mapData.height {

             for i in 0..<mapData.width {
                // 获取小图的文件名
                let imageName = "map-\(mapData.datas[j * mapData.width + i] + 1)"
                 
                if let imagePath = Bundle.main.path(forResource: imageName, ofType: "png") {
                    // 加载图片
                    if let image = NSImage(contentsOfFile: imagePath) {
                        // 在这里使用加载的图片
                        let x = CGFloat(i) * smallImageSize.width
                        let y = CGFloat(mapData.height-j-1) * (smallImageSize.height)
                        let rect = CGRect(x: x, y: y, width: smallImageSize.width, height: smallImageSize.height)
                        image.draw(in: rect)
                    }
                }
            }
        }
        
        // 获取拼接后的大图
        let bigImage = NSImage(size: bigImageSize)
        bigImage.addRepresentation(bitmapRep!)
        return bigImage
    }
    func loadMapData() {
        for i in 0...9 {
            let fileName = "m\(i)"
            
            guard let fileURL = Bundle.main.url(forResource: fileName, withExtension: nil),
                  let fileHandle = try? FileHandle(forReadingFrom: fileURL) else {
                continue
            }
            
            var mapData = MapData()
            var datas: [Int] = []
            var index = 0
            
            while let byte = fileHandle.readData(ofLength: 1).first {
                let intValue = Int(byte)
                if index == 0 {
                    mapData.width = intValue
                } else if index == 1 {
                    mapData.height = intValue
                } else {
                    datas.append(intValue)
                }
                index += 1
            }
            
            mapData.datas = datas
            mapDatas.append(mapData)
            
        }
    }
    
}

