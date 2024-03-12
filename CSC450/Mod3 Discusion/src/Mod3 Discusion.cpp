//============================================================================
// Name        : Mod3.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int main() {
	int temp1, temp2;//example integer based variables
	int * ptr1, ptr2;//example integer based pointers

	temp1 = 458;//set value to first variable
	ptr1 = &temp1;//set the first pointer
	cout << "Value " << temp1 << " is stored at " << ptr1;
}
