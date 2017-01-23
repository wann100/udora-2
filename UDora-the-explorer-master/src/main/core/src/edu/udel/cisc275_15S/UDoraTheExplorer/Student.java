package edu.udel.cisc275_15S.UDoraTheExplorer;


public class Student {
	private String username;
	private int advisementTries;
	private int advisementFinalDecisions;
	private int advisementBadRoomate;
	private int advisementScheduling;
	private int advisementLimit;
	private int resourcesTries;
	private int resourcesWritingCenter;
	private int resourcesMathTutorialLab;
	private int resourcesOfficeOfAcademicEnrichment;
	private int resourcesCareerServices;
	private int calendarTries;
	private int calendarDropAdd;
	private int calendarWithdrawal;
	private int calendarOverall;
	private int calendarOffTranscript;
	private int onlineTries;
	private int onlineUDSIS;
	private int onlineAddClasses;
	private int onlineChangeMajor;
	private int onlineOtherPeople;
	private String lastDateTried;
	private int totalTime;
	
	public Student(String username, int advisementTries, int advisementFinalDecisions,
			int advisementBadRoomate, int advisementScheduling, int advisementLimit,
			int resourcesTries, int resourcesWritingCenter, int resourcesMathTutorialLab,
			int resourcesOfficeOfAcademicEnrichment, int resourcesCareerServices, int calendarTries,
			int calendarDropAdd, int calendarWithdrawal, int calendarOverall, int calendarOffTranscript,
			int onlineTries, int onlineUDSIS, int onlineAddClasses, int onlineChangeMajor,
			int onlineOtherPeople, String lastDateTried, int totalTime) {
		this.username = username;
		this.advisementTries = advisementTries;
		this.advisementFinalDecisions = advisementFinalDecisions;
		this.advisementBadRoomate = advisementBadRoomate;
		this.advisementScheduling = advisementScheduling;
		this.advisementLimit = advisementLimit;
		this.resourcesTries = resourcesTries;
		this.resourcesWritingCenter = resourcesWritingCenter;
		this.resourcesMathTutorialLab = resourcesMathTutorialLab;
		this.resourcesOfficeOfAcademicEnrichment = resourcesOfficeOfAcademicEnrichment;
		this.resourcesCareerServices = resourcesCareerServices;
		this.calendarTries = calendarTries;
		this.calendarDropAdd = calendarDropAdd;
		this.calendarWithdrawal = calendarWithdrawal;
		this.calendarOverall = calendarOverall;
		this.calendarOffTranscript = calendarOffTranscript;
		this.onlineTries = onlineTries;
		this.onlineUDSIS = onlineUDSIS;
		this.onlineAddClasses = onlineAddClasses;
		this.onlineChangeMajor = onlineChangeMajor;
		this.onlineOtherPeople = onlineOtherPeople;
		this.lastDateTried = lastDateTried;
		this.totalTime = totalTime;
	}

	public Student() {
		this.username = "Student";
		this.advisementTries = 0;
		this.advisementFinalDecisions = 0;
		this.advisementBadRoomate = 0;
		this.advisementScheduling = 0;
		this.advisementLimit = 0;
		this.resourcesTries = 0;
		this.resourcesWritingCenter = 0;
		this.resourcesMathTutorialLab = 0;
		this.resourcesOfficeOfAcademicEnrichment = 0;
		this.resourcesCareerServices = 0;
		this.calendarTries = 0;
		this.calendarDropAdd = 0;
		this.calendarWithdrawal = 0;
		this.calendarOverall = 0;
		this.calendarOffTranscript = 0;
		this.onlineTries = 0;
		this.onlineUDSIS = 0;
		this.onlineAddClasses = 0;
		this.onlineChangeMajor = 0;
		this.onlineOtherPeople = 0;
		this.lastDateTried = new String();
		this.totalTime = 0;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public String getLastDateTried() {
		return lastDateTried;
	}

	public void setLastDateTried(String lastDateTried) {
		this.lastDateTried = lastDateTried;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAdvisementTries() {
		return advisementTries;
	}

	public int getAdvisementFinalDecisions() {
		return advisementFinalDecisions;
	}

	public int getAdvisementBadRoomate() {
		return advisementBadRoomate;
	}

	public int getAdvisementScheduling() {
		return advisementScheduling;
	}

	public int getAdvisementLimit() {
		return advisementLimit;
	}

	public int getResourcesTries() {
		return resourcesTries;
	}

	public int getResourcesWritingCenter() {
		return resourcesWritingCenter;
	}

	public int getResourcesMathTutorialLab() {
		return resourcesMathTutorialLab;
	}

	public int getResourcesOfficeOfAcademicEnrichment() {
		return resourcesOfficeOfAcademicEnrichment;
	}

	public int getResourcesCareerServices() {
		return resourcesCareerServices;
	}

	public int getCalendarTries() {
		return calendarTries;
	}

	public int getCalendarDropAdd() {
		return calendarDropAdd;
	}

	public int getCalendarWithdrawal() {
		return calendarWithdrawal;
	}

	public int getCalendarOverall() {
		return calendarOverall;
	}

	public int getCalendarOffTranscript() {
		return calendarOffTranscript;
	}

	public int getOnlineTries() {
		return onlineTries;
	}

	public int getOnlineUDSIS() {
		return onlineUDSIS;
	}

	public int getOnlineAddClasses() {
		return onlineAddClasses;
	}

	public int getOnlineChangeMajor() {
		return onlineChangeMajor;
	}

	public int getOnlineOtherPeople() {
		return onlineOtherPeople;
	}

	public void setAdvisementTries(int advisementTries) {
		this.advisementTries = advisementTries;
	}

	public void setAdvisementFinalDecisions(int advisementFinalDecisions) {
		this.advisementFinalDecisions = advisementFinalDecisions;
	}

	public void setAdvisementBadRoomate(int advisementBadRoomate) {
		this.advisementBadRoomate = advisementBadRoomate;
	}

	public void setAdvisementScheduling(int advisementScheduling) {
		this.advisementScheduling = advisementScheduling;
	}

	public void setAdvisementLimit(int advisementLimit) {
		this.advisementLimit = advisementLimit;
	}

	public void setResourcesTries(int resourcesTries) {
		this.resourcesTries = resourcesTries;
	}

	public void setResourcesWritingCenter(int resourcesWritingCenter) {
		this.resourcesWritingCenter = resourcesWritingCenter;
	}

	public void setResourcesMathTutorialLab(int resourcesMathTutorialLab) {
		this.resourcesMathTutorialLab = resourcesMathTutorialLab;
	}

	public void setResourcesOfficeOfAcademicEnrichment(
			int resourcesOfficeOfAcademicEnrichment) {
		this.resourcesOfficeOfAcademicEnrichment = resourcesOfficeOfAcademicEnrichment;
	}

	public void setResourcesCareerServices(int resourcesCareerServices) {
		this.resourcesCareerServices = resourcesCareerServices;
	}

	public void setCalendarTries(int calendarTries) {
		this.calendarTries = calendarTries;
	}

	public void setCalendarDropAdd(int calendarDropAdd) {
		this.calendarDropAdd = calendarDropAdd;
	}

	public void setCalendarWithdrawal(int calendarWithdrawal) {
		this.calendarWithdrawal = calendarWithdrawal;
	}

	public void setCalendarOverall(int calendarOverall) {
		this.calendarOverall = calendarOverall;
	}

	public void setCalendarOffTranscript(int calendarOffTranscript) {
		this.calendarOffTranscript = calendarOffTranscript;
	}

	public void setOnlineTries(int onlineTries) {
		this.onlineTries = onlineTries;
	}

	public void setOnlineUDSIS(int onlineUDSIS) {
		this.onlineUDSIS = onlineUDSIS;
	}

	public void setOnlineAddClasses(int onlineAddClasses) {
		this.onlineAddClasses = onlineAddClasses;
	}

	public void setOnlineChangeMajor(int onlineChangeMajor) {
		this.onlineChangeMajor = onlineChangeMajor;
	}

	public void setOnlineOtherPeople(int onlineOtherPeople) {
		this.onlineOtherPeople = onlineOtherPeople;
	}
	
	public void increaseAdvisementTries() {
		this.advisementTries++;
	}

	public void increaseAdvisementFinalDecisions() {
		this.advisementFinalDecisions++;
	}

	public void increaseAdvisementBadRoomate() {
		this.advisementBadRoomate++;
	}

	public void increaseAdvisementScheduling() {
		this.advisementScheduling++;
	}

	public void increaseAdvisementLimit() {
		this.advisementLimit++;
	}

	public void increaseResourcesTries() {
		this.resourcesTries++;
	}

	public void increaseResourcesWritingCenter() {
		this.resourcesWritingCenter++;
	}

	public void increaseResourcesMathTutorialLab() {
		this.resourcesMathTutorialLab++;
	}

	public void increaseResourcesOfficeOfAcademicEnrichment() {
		this.resourcesOfficeOfAcademicEnrichment++;
	}

	public void increaseResourcesCareerServices() {
		this.resourcesCareerServices++;
	}

	public void increaseCalendarTries() {
		this.calendarTries++;
	}

	public void increaseCalendarDropAdd() {
		this.calendarDropAdd++;
	}

	public void increaseCalendarWithdrawal() {
		this.calendarWithdrawal++;
	}

	public void increaseCalendarOverall() {
		this.calendarOverall++;
	}

	public void increaseCalendarOffTranscript() {
		this.calendarOffTranscript++;
	}

	public void increaseOnlineTries() {
		this.onlineTries++;
	}

	public void increaseOnlineUDSIS() {
		this.onlineUDSIS++;
	}

	public void increaseOnlineAddClasses() {
		this.onlineAddClasses++;
	}

	public void increaseOnlineChangeMajor() {
		this.onlineChangeMajor++;
	}

	public void increaseOnlineOtherPeople() {
		this.onlineOtherPeople++;
	}


	
	
	
	
	
}
