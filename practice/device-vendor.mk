PRODUCT_PACKAGES += \
    vendor.my.hardware.ledcontrol@1.0-service \
    LedControlService \
    LedControlApp

DEVICE_MANIFEST_FILE += \
    vendor/my/hikey960/hardware/interfaces/manifest.xml

DEVICE_MATRIX_FILE += \
    vendor/my/hikey960/hardware/interfaces/compatibility_matrix.xml

BOARD_SEPOLICY_DIRS += \
    vendor/my/hikey960/sepolicy

TARGET_FS_CONFIG_GEN += \
    vendor/my/hikey960/hardware/interfaces/config.fs
