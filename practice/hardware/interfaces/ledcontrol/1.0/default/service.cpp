#define LOG_TAG "vendor.my.hardware.ledcontrol@1.0-service"

#include <hidl/HidlSupport.h>
#include <hidl/HidlTransportSupport.h>
#include "LedControl.h"

using ::android::hardware::configureRpcThreadpool;
using ::vendor::my::hardware::ledcontrol::V1_0::implementation::LedControl;
using ::vendor::my::hardware::ledcontrol::V1_0::ILedControl;
using ::android::hardware::joinRpcThreadpool;
using ::android::OK;
using ::android::sp;

int main(int  /* argc */, char* /* argv */ []) 
{
	sp<ILedControl> ledctrl = new LedControl();
	configureRpcThreadpool(1, true /* will join */);
	if (ledctrl->registerAsService() != OK) 
	{
		ALOGE("Could not register LedControl 1.0 service.");
		return 1;
	}
	joinRpcThreadpool();

	ALOGE("service exited!");
	return 1;
}
