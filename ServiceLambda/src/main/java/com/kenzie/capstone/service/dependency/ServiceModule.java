package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.HireStatusService;
import com.kenzie.capstone.service.dao.HireStatusDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
    includes = DaoModule.class
)
public class ServiceModule {

    @Singleton
    @Provides
    @Inject
    public HireStatusService provideHireService(@Named("HireStatusDao") HireStatusDao hireDao) {
        return new HireStatusService(hireDao);
    }
}

