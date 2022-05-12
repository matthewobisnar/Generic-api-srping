package com.mattproject.services.uploader

import com.mattproject.services.uploader.interfaces.IFileInstanceContent
import com.mattproject.services.uploader.interfaces.IFilesStorageService
import com.mattproject.services.uploader.interfaces.IFileValidator
import org.springframework.web.multipart.MultipartFile
import com.mattproject.exceptions.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class FilesStorageService : IFilesStorageService, IFileInstanceContent<MutableMap<String, Any>> {

    protected var validator: MutableList<IFileValidator> = mutableListOf();

    /**
     * @param Multipart of Any files.
     *
     * @return MutableMap<String, any>
     * @throws BadRequestException if file no file found.
     *
     */
    fun getFileInstance(file:MultipartFile, returnResponseException: Boolean = false) : Any {

        var mutableMap: MutableMap<String, Any> = mutableMapOf()

        if (file.contentType.isNullOrEmpty()) {
            if (returnResponseException) {
                throw BadRequestException("No file found.")
            }

            return mutableMap
        }

        mutableMap.put("extension", StringUtils.getFilenameExtension(file.getOriginalFilename()).toString())
        mutableMap.put("size", file.size)
        mutableMap.put("originalName", file.originalFilename.toString())
        mutableMap.put("contentType", file.getContentType().toString())
        mutableMap.put("instance", file)

        return mutableMap
    }

    companion object {

        /**
         * @param Multipart of Any files.
         *
         * @return MutableMap<String, any>
         * @throws BadRequestException if file no file found.
         *
         */
        fun getFileInstance(file:MultipartFile, returnResponseException: Boolean = false) : Any {

            var mutableMap: MutableMap<String, Any> = mutableMapOf()

            if (file.contentType.isNullOrEmpty()) {
                if (returnResponseException) {
                    throw BadRequestException("No file found.")
                }

                return mutableMap
            }

            mutableMap.put("extension", StringUtils.getFilenameExtension(file.getOriginalFilename()).toString())
            mutableMap.put("size", file.size)
            mutableMap.put("originalName", file.originalFilename.toString())
            mutableMap.put("contentType", file.getContentType().toString())
            mutableMap.put("instance", file)

            return mutableMap
        }
    }

    /**
     *
     *
     */
    override fun getContent(): MutableMap<String, Any> {
        return mutableMapOf();
    }

    /**
     *
     *
     */
    fun addValidator(validator: IFileValidator): FilesStorageService {
        this.validator.add(validator);
        return this;
    }

    /**
     *
     *
     */
    fun getValidators(): MutableList<IFileValidator> {
        return this.validator;
    }

}