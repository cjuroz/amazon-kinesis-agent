package com.amazon.kinesis.streaming.agent.tailing;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;

import com.amazon.kinesis.streaming.agent.tailing.Kernel32.BY_HANDLE_FILE_INFORMATION;

public class WindowsFileHandle {

    public static String fileKey(String s) {
        WinBase.SECURITY_ATTRIBUTES attr = null;
        BY_HANDLE_FILE_INFORMATION lpFileInformation = new BY_HANDLE_FILE_INFORMATION();
        WinNT.HANDLE hFile = null;

        // https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx
        // TODO should check hFile is not null and Kernel32.INSTANCE.GetLastError()
        hFile = Kernel32.INSTANCE.CreateFile(s, 0,WinNT.FILE_SHARE_READ | WinNT.FILE_SHARE_WRITE | WinNT.FILE_SHARE_DELETE, attr, WinNT.OPEN_EXISTING, WinNT.FILE_ATTRIBUTE_NORMAL,null);
        com.amazon.kinesis.streaming.agent.tailing.Kernel32.INSTANCE.GetFileInformationByHandle(hFile, lpFileInformation);
        String identifier = lpFileInformation.nFileIndexHigh + lpFileInformation.nFileIndexLow.toString() + Integer.toHexString(lpFileInformation.dwVolumeSerialNumber.intValue());
        Kernel32.INSTANCE.CloseHandle(hFile);
        return identifier;
    }
}
