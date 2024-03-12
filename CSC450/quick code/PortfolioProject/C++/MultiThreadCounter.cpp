//create a class to manage counting to 20 and back down in a threaded environment
#include <iostream>
#include <thread>
#include <mutex>
#include <chrono>
using namespace std;

void countup(counter &c);
void countdown(counter &c);

class counter {
    mutex m;
    int count;
    public:
        void showcount();
        mutex& getMutex();
        counter();
        void increment();
        void decrement();
        int getCount();
};//end counter class

int main() {
    counter c;
    // thread t1(countup, ref(c));
    // thread t2(countdown, ref(c));
    countup(c);
    countdown(c);
    //t1.join();
    //t2.join();
    cout << "Finished." << endl;
    return 0;
}//end main function

void countup(counter &c) {
    //attempt to lock mutex
    //while (!c.getMutex().try_lock()) {}//end while
    for (; c.getCount() < 20; c.increment()) {
        c.showcount();
        //this_thread::sleep_for(chrono::milliseconds(500));
        cout << endl;
    }//end for
    //unlock mutex
    //c.getMutex().unlock();
}//end countup method

void countdown(counter &c) {
    //sleep for half a second
    //this_thread::sleep_for(chrono::milliseconds(500));
    //attempt to lock mutex
    //while (!c.getMutex().try_lock()) {}//end while
    for (; c.getCount() >= 0; c.decrement()) {
        c.showcount();
        //this_thread::sleep_for(chrono::milliseconds(500));
        cout << endl;
    }//end for
    //unlock mutex
    //c.getMutex().unlock();
}//end countdown method

counter::counter() {
    count = 0;
    cout << "Counter has been created." << endl;
    cout << "Count is " << count << endl;
}//end constructor

void counter::showcount() {
    cout << "From Thread# " << this_thread::get_id() << ", the count  is " << count << endl;
}//end showcount method

mutex& counter::getMutex() {
    return m;
}//end getMutex method

void counter::increment() {
    count++;
}//end increment method

void counter::decrement() {
    count--;
}//end decrement method

int counter::getCount() {
    return count;
}//end getCount method

