package com.livetyping.images.settings

import android.content.Context

/**
 * crete file in data/data/{applicationId}/files/additionalPath
 */
class FilesPathSettings(attrName: String,
                        additionalPath: String?,
                        fileName: String = "file_in_app_files_dir")

    : TakePhotoSettings(attrName, additionalPath, fileName) {

    override val pathAttr: String
        get() = "files-path"

    override fun getRootPath(context: Context) = context.filesDir.path
}
