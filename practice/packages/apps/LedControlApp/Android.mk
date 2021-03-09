LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := \
   $(call all-java-files-under, app/src/main/java) \
   $(call all-Iaidl-files-under, app/src/main/aidl)

LOCAL_PACKAGE_NAME := LedControlApp
LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml
LOCAL_CERTIFICATE := platform
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_USE_AAPT2 := true
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res
LOCAL_STATIC_ANDROID_LIBRARIES := \
   android-support-v7-appcompat \
   android-support-constraint-layout 

LOCAL_STATIC_JAVA_LIBRARIES += \
   android-support-constraint-layout-solver

include $(BUILD_PACKAGE)
