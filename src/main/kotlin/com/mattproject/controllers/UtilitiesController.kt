package com.mattproject.controllers

import com.mattproject.services.GenericRepositoryResolverService
import org.springframework.beans.factory.annotation.Autowired
import com.mattproject.services.uploader.FilesStorageService
import org.springframework.web.multipart.MultipartFile
import com.mattproject.extension.ResponseHandlerUtil
import com.mattproject.repositories.DocumentFileRepository
import com.mattproject.services.QueryConstructorService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("{countryCode}/api/v1/utilities")
class UtilitiesController {

    @Autowired
    lateinit var appGenericService: GenericRepositoryResolverService;

    @Autowired
    lateinit var filesStorageService: FilesStorageService;

    @Autowired
    lateinit var queryConstructorService: QueryConstructorService;



    @RequestMapping(
        value = arrayOf("/generics", "generics"),
        method = arrayOf(RequestMethod.GET)
    )
    fun actionSearchManager(
        @RequestParam(name = "search") search: String
    ) : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse(
            "not found", appGenericService.resourceDispatcher(search)
        )
    }

    @RequestMapping(
        value = arrayOf("/upload", "upload"),
        method = arrayOf(RequestMethod.POST)
    )
    fun actionUploadFile(
        @RequestParam(required=true, name="file") files : MultipartFile,
        @RequestParam(required=true, name="request_data") requestData : String
    ) : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("not found", filesStorageService.getFileInstance(files));
    }

    fun actionSample() : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("", null)
    }

    @RequestMapping(
        value = arrayOf("/get-sample", "get-sample"),
        method = arrayOf(RequestMethod.GET)
    )
    fun actionSampleUploadFiles(
        @RequestParam(required = true, name= "search") search: String
    ) : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("not found",
            queryConstructorService.queryInit(search).getResult()
        );
    }
}