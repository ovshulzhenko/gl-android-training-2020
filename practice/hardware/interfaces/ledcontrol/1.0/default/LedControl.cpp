#define LOG_TAG "MyLed"

#include <log/log.h>
#include "LedControl.h"
#include <stdio.h>
#include <fstream>

namespace vendor {
namespace my{
namespace hardware {
namespace ledcontrol {
namespace V1_0 {
namespace implementation {

LedControl::LedControl() 
{
	ALOGD("->LedControl::LedControl() ");
}

LedControl::~LedControl() 
{
	ALOGD("->LedControl::~LedControl() ");
}

Return<int32_t> LedControl::getLedBrightness(const hidl_string & led_id)
{
	std::ifstream led_desc;
	ALOGD("->LedControl::getLedBrightness(%s) rdstate() = %d", led_id.c_str(), led_desc.rdstate());

	int32_t res = INVALID_BRIGHTNESS;

	led_desc.open(led_id.c_str(), std::ifstream::in);

	if (!led_desc.is_open())
	{
		ALOGD("->LedControl::getLedBrightness(%s) can't open led file, rdstate() = %d", led_id.c_str(), led_desc.rdstate());
	}
	else 
	{
		led_desc >> res;
		ALOGD("->LedControl::getLedBrightness(%s) got res = %d", led_id.c_str(), res);
	}

	led_desc.close();

	return res;
}

Return<ERROR_CODE> LedControl::setLedBrightness(const hidl_string & led_id, int32_t val)
{
	ALOGD("->LedControl::setLedBrightness(%s)", led_id.c_str());

	ERROR_CODE res = ERROR_CODE::RESULT_ERROR;

	if ((val !=0) && (val != 1))
	{
		ALOGD("->LedControl::setLedBrightness(%s, %d), invalid value = %d", led_id.c_str(), val, val);
		return res;
	}

	std::ofstream led_desc;

	led_desc.open(led_id.c_str(), std::ofstream::out);

	if (!led_desc.is_open())
	{
		ALOGD("->LedControl::setLedBrightness(%s) can't open led file", led_id.c_str());
	}
	else 
	{
		led_desc << val;
		ALOGD("->LedControl::ыetLedBrightness(%s) saved brightness = %d", led_id.c_str(), val);
	}

	led_desc.close();
	
	return res;
}

} //namespace implementation
} //namespace V1_0
} //namespace ledcontrol
} //namespace hardware
} //namespace my
} //namespace vendor
