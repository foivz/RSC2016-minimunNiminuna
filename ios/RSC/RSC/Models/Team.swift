
import Foundation
import Unbox
struct Team {
    let idTeam: Int
    let name: String
    //let imageUrl: String
    let members: [User]?
}
extension Team: Unboxable {
    init(unboxer: Unboxer) throws {
        self.idTeam = try unboxer.unbox(key: "idTeam")
        self.name = try unboxer.unbox(key: "name")
        //self.imageUrl = try unboxer.unbox(key: "icon.url")
        self.members = unboxer.unbox(key: "members")
    }
}
