#include <jni.h>
#include <android/log.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <ifaddrs.h>
#include <string>
#include <sys/types.h>
#include <ifaddrs.h>
#include <unistd.h>
#include <sstream>
#include <arpa/inet.h>



std::string get_ip_address();

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
jobject
) {
std::string hello = "Hello from cpp";



    std::string ip_address = get_ip_address();

    return env->NewStringUTF(ip_address.c_str());
}

std::string get_ip_address() {
    struct  ifaddrs *ifaddrs ,*ifa ,*ifap;
    struct sockaddr_in *sa;
    char *addr;
    getifaddrs (&ifap);
    for (ifa = ifap; ifa; ifa = ifa->ifa_next) {
        if (ifa->ifa_addr && ifa->ifa_addr->sa_family==AF_INET) {
            sa = (struct sockaddr_in *) ifa->ifa_addr;
            addr = inet_ntoa(sa->sin_addr);
            return std::string (addr);
        }
    }

    freeifaddrs(ifap);
    return 0;



}

