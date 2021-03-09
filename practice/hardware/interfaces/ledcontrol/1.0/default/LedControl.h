#ifndef VENDOR_MY_HARDWARE_TEST_V1_0_LEDCONTROL_H
#define VENDOR_MY_HARDWARE_TEST_V1_0_LEDCONTROL_H
#include <vendor/my/hardware/ledcontrol/1.0/ILedControl.h>
#include <vendor/my/hardware/ledcontrol/1.0/types.h>

#define INVALID_BRIGHTNESS -1

namespace vendor {
namespace my {
namespace hardware {
namespace ledcontrol {
namespace V1_0 {
namespace implementation {

using ::android::hardware::Return;
using ::android::hardware::hidl_string;

/**
 *
 */
struct LedControl : public ILedControl {
	LedControl();

	Return<int32_t> getLedBrightness(const hidl_string & led_id) override;
	Return<ERROR_CODE> setLedBrightness(const hidl_string & led_id, int32_t val) override;

private:
	~LedControl();
};

} //namespace implementation
} //namespace V1_0
} //namespace ledcontrol
} //namespace hardware
} //namespace my
} //namespace vendor

#endif //VENDOR_MY_HARDWARE_TEST_V1_0_LEDCONTROL_H
