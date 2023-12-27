package org.faction.test;

import java.util.List;

import com.faction.elements.Assessment;
import com.faction.elements.User;
import com.faction.elements.Verification;
import com.faction.elements.Vulnerability;
import com.faction.extender.ApplicationInventory;
import com.faction.extender.AssessmentManager;
import com.faction.extender.AssessmentManagerResult;
import com.faction.extender.InventoryResult;
import com.faction.extender.VerificationManager;
import com.faction.extender.VulnerabilityManager;

public class AllExtensions implements AssessmentManager, VerificationManager, VulnerabilityManager, ApplicationInventory{
	
	@Override
	public AssessmentManagerResult assessmentChange(Assessment assessment, List<Vulnerability> vulnerabilities, 
			com.faction.extender.AssessmentManager.Operation operation) {
		
		if(operation == com.faction.extender.AssessmentManager.Operation.Finalize) {
			for(Vulnerability vuln : vulnerabilities) {
				vuln.setTracking("TEST-123");
			}
			assessment.setNotes("Updated By Finalize");
			AssessmentManagerResult result = new AssessmentManagerResult();
			result.setAssessment(assessment);
			result.setVulnerabilities(vulnerabilities);
			return result;
		} 
		else if(operation == com.faction.extender.AssessmentManager.Operation.Create) {
			assessment.setNotes("Updated By Create");
			AssessmentManagerResult result = new AssessmentManagerResult();
			result.setAssessment(assessment);
			result.setVulnerabilities(null);
			return result;
		} 
		else if(operation == com.faction.extender.AssessmentManager.Operation.Update) {
			assessment.setNotes("Updated By Update");
			AssessmentManagerResult result = new AssessmentManagerResult();
			result.setAssessment(assessment);
			for(Vulnerability vuln : vulnerabilities) {
				vuln.setDescription("Updated By Update");
			}
			result.setVulnerabilities(vulnerabilities);
			return result;
		}
		return null;
	}

	@Override
	public Vulnerability verificationChange(User user, Vulnerability vulnerability, Verification verification,
			com.faction.extender.VerificationManager.Operation operation) {
		if(operation == com.faction.extender.VerificationManager.Operation.Assigned) {
			vulnerability.setDescription("Updated By Assigned");
		}
		else if(operation == com.faction.extender.VerificationManager.Operation.Cancel) {
			vulnerability.setDescription("Updated By Cancel");
		}
		else if(operation == com.faction.extender.VerificationManager.Operation.FAIL) {
			vulnerability.setDescription("Updated By Fail");
		}
		else if(operation == com.faction.extender.VerificationManager.Operation.PASS) {
			vulnerability.setDescription("Updated By PASS");
		}
		return vulnerability;
	}

	@Override
	public Vulnerability vulnChange(Assessment assessment, Vulnerability vulnerability,
			com.faction.extender.VulnerabilityManager.Operation operation) {
		
		if(operation == com.faction.extender.VulnerabilityManager.Operation.Create) {
			vulnerability.setDescription("Updated By Create");
		}
		else if(operation == com.faction.extender.VulnerabilityManager.Operation.Update) {
			vulnerability.setDescription("Updated By Update");
		}
		else if(operation == com.faction.extender.VulnerabilityManager.Operation.Delete) {
			vulnerability.setDescription("Updated By Delete");
		}
		return vulnerability;
	}

	@Override
	public InventoryResult[] search(String arg0, String arg1) {
		InventoryResult result1 = new InventoryResult();
		result1.setApplicationId("1234");
		result1.setApplicationName("Test Name");
		InventoryResult result2 = new InventoryResult();
		result2.setApplicationId("1235");
		result2.setApplicationName("Another Name");
		return new InventoryResult [] { result1, result2 };
	}


}
