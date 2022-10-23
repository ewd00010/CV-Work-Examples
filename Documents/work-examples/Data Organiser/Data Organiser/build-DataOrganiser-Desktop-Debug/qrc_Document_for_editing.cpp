/****************************************************************************
** Resource object code
**
** Created by: The Resource Compiler for Qt version 5.15.6
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

static const unsigned char qt_resource_data[] = {
  // /home/ewan/Desktop/QT stuff/Data Organiser/DataOrganiser/Temporary.txt
  0x0,0x0,0x0,0x16,
  0x44,
  0x65,0x6c,0x65,0x74,0x65,0x20,0x74,0x68,0x69,0x73,0x20,0x54,0x65,0x6d,0x70,0x20,
  0x54,0x65,0x78,0x74,0xa,
  
};

static const unsigned char qt_resource_name[] = {
  // Temporary.txt
  0x0,0xd,
  0xd,0x14,0xb7,0x94,
  0x0,0x54,
  0x0,0x65,0x0,0x6d,0x0,0x70,0x0,0x6f,0x0,0x72,0x0,0x61,0x0,0x72,0x0,0x79,0x0,0x2e,0x0,0x74,0x0,0x78,0x0,0x74,
  
};

static const unsigned char qt_resource_struct[] = {
  // :
  0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x1,
0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,
  // :/Temporary.txt
  0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x0,
0x0,0x0,0x1,0x81,0x1b,0x10,0xbb,0x67,

};

#ifdef QT_NAMESPACE
#  define QT_RCC_PREPEND_NAMESPACE(name) ::QT_NAMESPACE::name
#  define QT_RCC_MANGLE_NAMESPACE0(x) x
#  define QT_RCC_MANGLE_NAMESPACE1(a, b) a##_##b
#  define QT_RCC_MANGLE_NAMESPACE2(a, b) QT_RCC_MANGLE_NAMESPACE1(a,b)
#  define QT_RCC_MANGLE_NAMESPACE(name) QT_RCC_MANGLE_NAMESPACE2( \
        QT_RCC_MANGLE_NAMESPACE0(name), QT_RCC_MANGLE_NAMESPACE0(QT_NAMESPACE))
#else
#   define QT_RCC_PREPEND_NAMESPACE(name) name
#   define QT_RCC_MANGLE_NAMESPACE(name) name
#endif

#ifdef QT_NAMESPACE
namespace QT_NAMESPACE {
#endif

bool qRegisterResourceData(int, const unsigned char *, const unsigned char *, const unsigned char *);
bool qUnregisterResourceData(int, const unsigned char *, const unsigned char *, const unsigned char *);

#ifdef QT_NAMESPACE
}
#endif

int QT_RCC_MANGLE_NAMESPACE(qInitResources_Document_for_editing)();
int QT_RCC_MANGLE_NAMESPACE(qInitResources_Document_for_editing)()
{
    int version = 3;
    QT_RCC_PREPEND_NAMESPACE(qRegisterResourceData)
        (version, qt_resource_struct, qt_resource_name, qt_resource_data);
    return 1;
}

int QT_RCC_MANGLE_NAMESPACE(qCleanupResources_Document_for_editing)();
int QT_RCC_MANGLE_NAMESPACE(qCleanupResources_Document_for_editing)()
{
    int version = 3;
    QT_RCC_PREPEND_NAMESPACE(qUnregisterResourceData)
       (version, qt_resource_struct, qt_resource_name, qt_resource_data);
    return 1;
}

namespace {
   struct initializer {
       initializer() { QT_RCC_MANGLE_NAMESPACE(qInitResources_Document_for_editing)(); }
       ~initializer() { QT_RCC_MANGLE_NAMESPACE(qCleanupResources_Document_for_editing)(); }
   } dummy;
}
