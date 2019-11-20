package com.example.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResp {

    private String code;
    
    private String msg;
    
    private Object obj;
    
}
