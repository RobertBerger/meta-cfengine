SUMMARY = "open source configuration management system"
DESCRIPTION = "CFEngine 3 is a popular open source configuration management system. \
Its primary function is to provide automated configuration and maintenance of large-scale computer systems."
HOMEPAGE = "http://cfengine.com/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

# SRC_URI is defined in the append file
SRC_URI = ""

DEPENDS = "libxml2 openssl acl libpcre lmdb libpam"
inherit autotools

# quick and dirty fix:
INSANE_SKIP_${PN} = "useless-rpaths"

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = " --with-libxml2=${STAGING_LIBDIR}/.."
# in case of compilation issues uncomment the following line
#PARALLEL_MAKE = ""

# hack for cfengine - first time when target boots
# this could be done nicer like e.g. 
# config prefix to /var/cfengine if needed
pkg_postinst_${PN} () {
#!/bin/sh -e
if [ ! -f /var/cfengine/inputs/promises.cf ]; then
  touch /var/cfengine/inputs/promises.cf
fi
if [ ! -d /var/cfengine/bin ]; then
  mkdir -p /var/cfengine/bin
fi
if [ ! -f /var/cfengine/bin/cf-promises ]; then
  cp /usr/bin/cf-promises /var/cfengine/bin/cf-promises
fi
}
