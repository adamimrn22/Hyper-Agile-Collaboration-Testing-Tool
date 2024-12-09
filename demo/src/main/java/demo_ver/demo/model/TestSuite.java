// package demo_ver.demo.model;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.boot.autoconfigure.domain.EntityScan;

// @EntityScan
// public class TestSuite {
//     private Long id; // Unique identifier for the test suite
//     private String name; // Name of the test suite
//     private String description; // Description of the test suite
//     private String createdBy; // Creator of the test suite
//     private String dateCreated; // Date the test suite was created
//     private String dateUpdated; // Date the test suite was last updated
//     private String deadline; // Deadline for the test suite
//     private List<TestCase> testCases = new ArrayList<>(); // List of test cases in the suite
//     private String overallStatus; // Overall status of the suite based on test cases
//     private List<Integer> userID; // List of assigned user IDs (add this if missing)
//     private Boolean isActive;  // Can be used for "active" checkbox
//     private Boolean isPublic;  // New field for "public" checkbox

//     public TestSuite() {
//         // Default constructor
//     }

//     public TestSuite(Long id, String name, String description, String createdBy, String dateCreated) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.createdBy = createdBy;
//         this.dateCreated = dateCreated;
//         this.isActive = isActive;
//         this.isPublic = isPublic;
//     }

//     // Getters and setters for fields
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getCreatedBy() {
//         return createdBy;
//     }

//     public void setCreatedBy(String createdBy) {
//         this.createdBy = createdBy;
//     }

//     public String getDateCreated() {
//         return dateCreated;
//     }

//     public void setDateCreated(String dateCreated) {
//         this.dateCreated = dateCreated;
//     }

//     public String getDateUpdated() {
//         return dateUpdated;
//     }

//     public void setDateUpdated(String dateUpdated) {
//         this.dateUpdated = dateUpdated;
//     }

//     public String getDeadline() {
//         return deadline;
//     }

//     public void setDeadline(String deadline) {
//         this.deadline = deadline;
//     }

//     public List<TestCase> getTestCases() {
//         return testCases;
//     }

//     public void setTestCases(List<TestCase> testCases) {
//         this.testCases = testCases;
//     }

//     public List<Integer> getUserID() {
//         return userID;
//     }

//     public void setUserID(List<Integer> userID) {
//         this.userID = userID;
//     }

//     // Add a test case to the suite
//     public void addTestCase(TestCase testCase) {
//         this.testCases.add(testCase);
//         updateOverallStatus();
//     }

//     // Remove a test case from the suite
//     public void removeTestCase(TestCase testCase) {
//         this.testCases.remove(testCase);
//         updateOverallStatus();
//     }

//     // Calculate the overall status of the suite based on the statuses of its test cases
//     public void updateOverallStatus() {
//         if (testCases.stream().anyMatch(tc -> tc.getOverallStatus().equals("Rejected"))) {
//             this.overallStatus = "Rejected";
//         } else if (testCases.stream().allMatch(tc -> tc.getOverallStatus().equals("Approved"))) {
//             this.overallStatus = "Approved";
//         } else if (testCases.stream().anyMatch(tc -> tc.getOverallStatus().equals("Pending") || tc.getOverallStatus().equals("Needs Revision"))) {
//             this.overallStatus = "Pending";
//         } else {
//             this.overallStatus = "Pending"; // Default to Pending if no specific criteria met
//         }
//     }

//     public String getOverallStatus() {
//         return overallStatus;
//     }

//     public void setOverallStatus(String overallStatus) {
//         this.overallStatus = overallStatus;
//     }

//     // Method to get all test case names in the suite
//     public List<String> getTestCaseNames() {
//         return testCases.stream().map(TestCase::getTestCaseName).collect(Collectors.toList());
//     }

//     // Get suite name
//     public String getSuiteName() {
//         return this.name;
//     }

//     // Set user-specific status (assuming you have a list of statuses mapped by username)
//     public void setUserStatus(String username, String status) {
//         // Update user-specific status for the test suite if applicable
//         // Implement the logic to store the status per user here
//     }

//     // Determine the overall status (if this differs from updateOverallStatus)
//     public String determineOverallStatus() {
//         updateOverallStatus();
//         return overallStatus;
//     }

//     public Boolean getIsActive() {
//         return isActive;
//     }

//     public void setIsActive(Boolean isActive) {
//         this.isActive = isActive;
//     }

//     public Boolean getIsPublic() {
//         return isPublic;
//     }

//     public void setIsPublic(Boolean isPublic) {
//         this.isPublic = isPublic;
//     }

//     public void setActive(boolean isActive2) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'setActive'");
//     }

//     // Add other necessary methods as needed
// }

package demo_ver.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSuite {
    private Long id;
    private String name;
    private String description;
    private String status; // Overall status of the suite (e.g., Active, Inactive)
    private String importance; // Importance level (e.g., High, Medium, Low)
    private List<Integer> assignedUserIds = new ArrayList<>(); // List of assigned user IDs
    private Map<Integer, String> userStatuses = new HashMap<>(); // Map of user IDs to their statuses (e.g., Pending,
                                                                 // Completed)
    private TestPlan testPlan;

    // Constructor
    public TestSuite(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = "Pending"; // Default status when created
        this.importance = "Medium"; // Default importance level
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public List<Integer> getAssignedUserIds() {
        return assignedUserIds;
    }

    public void setAssignedUserIds(List<Integer> assignedUserIds) {
        this.assignedUserIds = assignedUserIds;
    }

    public Map<Integer, String> getUserStatuses() {
        return userStatuses;
    }

    public void setUserStatuses(Map<Integer, String> userStatuses) {
        this.userStatuses = userStatuses;
    }

    // Assign a user to the test suite
    public void assignUser(Integer userId) {
        if (!assignedUserIds.contains(userId)) {
            assignedUserIds.add(userId);
            userStatuses.put(userId, "Pending"); // Default status for a newly assigned user
        }
    }

    // Unassign a user from the test suite
    public void unassignUser(Integer userId) {
        assignedUserIds.remove(userId);
        userStatuses.remove(userId); // Remove the user's status as well
    }

    // Update the status of a specific user
    public void updateUserStatus(Integer userId, String status) {
        if (userStatuses.containsKey(userId)) {
            userStatuses.put(userId, status);
        } else {
            throw new IllegalArgumentException("User ID not assigned to the test suite");
        }
    }

    // Check if all users have a specific status (e.g., Completed)
    public boolean areAllUsersStatus(String status) {
        return userStatuses.values().stream().allMatch(s -> s.equals(status));
    }

    // Update overall status based on user statuses
    public void updatestatus() {
        if (areAllUsersStatus("Completed")) {
            status = "Completed";
        } else if (areAllUsersStatus("In Progress")) {
            status = "In Progress";
        } else {
            status = "Pending";
        }
    }

    // Debugging utility
    @Override
    public String toString() {
        return "TestSuite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", overallStatus='" + status + '\'' +
                ", importance='" + importance + '\'' +
                ", assignedUserIds=" + assignedUserIds +
                ", userStatuses=" + userStatuses +
                '}';
    }

    public TestPlan getTestPlan() {
        return getTestPlan();
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
