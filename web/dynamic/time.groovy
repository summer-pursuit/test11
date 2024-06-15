def now = new Date()
def formattedNow = now.format("yyyy-MM-dd HH:mm:ss")
output = "<html><body><h1>Current Time</h1><p>${formattedNow}</p></body></html>"