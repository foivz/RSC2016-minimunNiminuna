//
//  APIRequest.swift
//  PokeDex
//
//  Created by Infinum on 26/07/16.
//  Copyright © 2016 infinumSA. All rights reserved.
//

import Foundation
import Alamofire
import Unbox


class RESTClient {
    
    static func requestJSON(method: HTTPMethod, urlString: URLConvertible,parameters: [String : NSDictionary]? = nil, encoding: ParameterEncoding = JSONEncoding.default, headers: [String: String]? = nil , vCForMessage: UIViewController,
                       onSuccess: @escaping (NSData) -> (),
                       onError: ((Void) -> ())? = nil,
                       onFailure: ((Void)->())?=nil
        
        ) {
        Alamofire.request(urlString, method: method, parameters: parameters, encoding: encoding, headers: nil).responseJSON { (response:DataResponse<Any>) in
 
        switch response.result {
        case .success(_):
            if let data = response.result.value{
                print(data)
                onSuccess(data as! NSData)
            }
            break
            
        case .failure(_):
            print(response.result.error)
            onFailure!()
            break
        }
        }
        
    }
    
}
