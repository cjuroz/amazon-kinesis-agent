package com.amazon.kinesis.streaming.agent.tailing;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;

import com.amazon.kinesis.streaming.agent.tailing.Kernel32.BY_HANDLE_FILE_INFORMATION;

public class WindowsFileHandle {
//    private static final int FILE_SHARE_READ = (0x00000001);
//    private static final int FILE_SHARE_WRITE = (0x00000002);
//    private static final int FILE_SHARE_DELETE = (0x00000004);
//    private static final int OPEN_EXISTING = (3);
//    private static final int GENERIC_READ = (0x80000000);
    //final int GENERIC_WRITE = (0x40000000);
    //final int FILE_FLAG_NO_BUFFERING = (0x20000000);
    //final int FILE_FLAG_WRITE_THROUGH = (0x80000000);
    //final int FILE_READ_ATTRIBUTES = (0x0080);
    //final int FILE_WRITE_ATTRIBUTES = (0x0100);
    //final int ERROR_INSUFFICIENT_BUFFER = (122);
//    private static final int FILE_ATTRIBUTE_ARCHIVE = (0x20);

    public static String fileKey(String s) {
        WinBase.SECURITY_ATTRIBUTES attr = null;
        BY_HANDLE_FILE_INFORMATION lpFileInformation = new BY_HANDLE_FILE_INFORMATION();
        WinNT.HANDLE hFile = null;

        // https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx
       //  hFile = Kernel32.INSTANCE.CreateFile(s, GENERIC_READ, FILE_SHARE_READ, attr, OPEN_EXISTING, FILE_ATTRIBUTE_ARCHIVE, null);

        hFile = Kernel32.INSTANCE.CreateFile(s, 0,WinNT.FILE_SHARE_READ | WinNT.FILE_SHARE_WRITE | WinNT.FILE_SHARE_DELETE, attr, WinNT.OPEN_EXISTING, WinNT.FILE_ATTRIBUTE_NORMAL,null);

//
//        System.out.println("CreateFile last error:" + Kernel32.INSTANCE.GetLastError());
//
        com.amazon.kinesis.streaming.agent.tailing.Kernel32.INSTANCE.GetFileInformationByHandle(hFile, lpFileInformation);
//
//        System.out.println("CREATION TIME: " + WinBase.FILETIME.filetimeToDate(lpFileInformation.ftCreationTime.dwHighDateTime, lpFileInformation.ftCreationTime.dwLowDateTime));
//
//        System.out.println("VOLUME SERIAL NO.: " + Integer.toHexString(lpFileInformation.dwVolumeSerialNumber.intValue()));
//
//        System.out.println("FILE INDEX HIGH: " + lpFileInformation.nFileIndexHigh);
//        System.out.println("FILE INDEX LOW: " + lpFileInformation.nFileIndexLow);
//
//
//        System.out.println("GetFileInformationByHandle last error:" + Kernel32.INSTANCE.GetLastError());


        String identifier = lpFileInformation.nFileIndexHigh + lpFileInformation.nFileIndexLow.toString() + Integer.toHexString(lpFileInformation.dwVolumeSerialNumber.intValue());

        Kernel32.INSTANCE.CloseHandle(hFile);

//        System.out.println("CloseHandle last error:" + Kernel32.INSTANCE.GetLastError());

        return identifier;
    }
}
