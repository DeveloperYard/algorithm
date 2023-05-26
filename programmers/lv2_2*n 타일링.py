def solution(n):

    arr = [0]*(n+1)
    arr[1] = 1
    arr[0] = 1
    for i in range(2, n+1):
        arr[i] = (arr[i-1] + arr[i-2])%1000000007

    answer = arr[n]
    return answer


n = int(input())
print(solution(n))