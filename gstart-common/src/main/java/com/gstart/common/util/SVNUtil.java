package com.gstart.common.util;


import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-03-30 15:53
 */
public class SVNUtil {
    public static SVNRepository getRepository(String url, String name, String password) throws SVNException {
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
            ISVNAuthenticationManager authManager =
                    SVNWCUtil.createDefaultAuthenticationManager(name, password);
            repository.setAuthenticationManager(authManager);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return repository;
    }
}
