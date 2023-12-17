package model;

public class PermissionService {	
	public static final int PERMISSION_HR = 0;
	public static final int PERMISSION_FINANCIAL = 1;
	public static final int PERMISSION_MEDICAL = 2;
	
	private static PermissionService permissionSerivce = null;
	
	private int userPermission;
	
	private boolean isInitialized = false;
	
	private PermissionService() {};
	
	public static PermissionService getInstance()
	{
		if(permissionSerivce == null)
		{
			permissionSerivce = new PermissionService();
		}
		return permissionSerivce;
	}
	
	public void setUserPermission(int _userPermission) throws Exception
	{
		if(!isInitialized)
		{
			switch (_userPermission)
			{
			case PERMISSION_HR :
				userPermission = PERMISSION_HR;
				break;
			case PERMISSION_FINANCIAL : 
				userPermission = PERMISSION_FINANCIAL;
				break;
			case PERMISSION_MEDICAL : 
				userPermission = PERMISSION_MEDICAL;
				break;			
			}
			
			isInitialized = true;
		}
		else
		{
			throw new Exception("Permission already initialized");
		}
	}
}
