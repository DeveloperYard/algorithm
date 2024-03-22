#include<stdio.h>
#define max(a,b) a>b?a:b
int dp[10001];

int func(int arr[], int n)
{
	dp[0] = arr[0];
	dp[1] = max(arr[0] + arr[1], arr[1]);
	dp[2] = max(arr[0] + arr[2], arr[1] + arr[2]);
	dp[3] = max(arr[0] + arr[1] + arr[3], arr[0] + arr[2] + arr[3]);
	for (int i = 4; i < n; i++)
	{
		dp[i] = max(arr[i] + arr[i - 1] + dp[i - 3], arr[i] + dp[i - 2]);
		dp[i] = max(dp[i], arr[i] + arr[i - 1] + dp[i - 4]);
	}

	int tmp = max(dp[n - 1], dp[n - 2]);


	return tmp;
}

int main()
{
	int n = 0;
	scanf("%d", &n);
	int arr[10001] = { 0 };

	for (int i = 0; i < n; i++)
	{
		scanf("%d", arr + i);
	}
	printf("%d",func(arr, n));


	//system("pause");
	return 0;
}