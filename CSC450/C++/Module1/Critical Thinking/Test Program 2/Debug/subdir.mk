################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../CSC450_CT1_mod1-2.cpp 

CPP_DEPS += \
./CSC450_CT1_mod1-2.d 

OBJS += \
./CSC450_CT1_mod1-2.o 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp subdir.mk
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


clean: clean--2e-

clean--2e-:
	-$(RM) ./CSC450_CT1_mod1-2.d ./CSC450_CT1_mod1-2.o

.PHONY: clean--2e-
