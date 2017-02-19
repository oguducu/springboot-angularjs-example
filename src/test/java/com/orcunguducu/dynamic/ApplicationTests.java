package com.orcunguducu.dynamic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.orcunguducu.dynamic.controller.ParameterControllerIT;
import com.orcunguducu.dynamic.controller.ParameterControllerTest;
import com.orcunguducu.dynamic.controller.QueryControllerIT;
import com.orcunguducu.dynamic.controller.QueryControllerTest;
import com.orcunguducu.dynamic.controller.UserControllerIT;
import com.orcunguducu.dynamic.controller.UserSettingsControllerTest;
import com.orcunguducu.dynamic.service.ParameterServiceImplTest;
import com.orcunguducu.dynamic.service.QueryServiceImplTest;
import com.orcunguducu.dynamic.util.DataGeneratorTest;
 
@RunWith(Suite.class)
@SuiteClasses({/*ParameterControllerIT.class,
			   QueryControllerIT.class,
			   UserControllerIT.class,*/
			   ParameterControllerTest.class,
			   QueryControllerTest.class,
			   ParameterServiceImplTest.class,
			   QueryServiceImplTest.class,
			   UserSettingsControllerTest.class,
			   DataGeneratorTest.class})
public class ApplicationTests {

}
