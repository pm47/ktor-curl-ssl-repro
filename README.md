A tool to reproduce SSL issues with the curl client on ktor.

Supported platforms:
- linuxX64
- linuxArm64
- macosX64
- macosArm64,
- mingwX64

Usage:
```shell
# test with default parameters
./ktor-curl-ssl-repro.exe

# set curl.engine.caInfo
./ktor-curl-ssl-repro.exe caInfo=/path/to/bundle.crt

# set curl.engine.caPath
./ktor-curl-ssl-repro.exe caPath=/path/to/certs/dir

# set curl.engine.caInfo and curl.engine.caPath
./ktor-curl-ssl-repro.exe caInfo=/path/to/bundle.crt caPath=/path/to/certs/dir
```
