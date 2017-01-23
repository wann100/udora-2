from header import *

class Users(ndb.Model):
    username = ndb.StringProperty(default='')
    online_overallpercent =ndb.FloatProperty(default=0)
    online_amountofplays = ndb.IntegerProperty(default=0)
    online_numofsuccess = ndb.IntegerProperty(default=0)
#####################
    advisement_overallpercent = ndb.FloatProperty(default=0)
    advisement_amountofplays =ndb.IntegerProperty(default=0)
    advsuccess =ndb.IntegerProperty(default=0)
##############################################
    resources_overallpercent =ndb.FloatProperty(default=0)
    resources_amountofplays  = ndb.IntegerProperty(default=0)
    resources_numofsuccess =  ndb.IntegerProperty(default=0)
#################################################
    calendar_overallpercent =ndb.FloatProperty(default=0)
    calendar_amountofplays =ndb.IntegerProperty(default=0)
    calendar_numofsuccess= ndb.IntegerProperty(default=0)
def getUser(username,password):
    if not(username):
        return None
    users = Users.query(Users.username == username)
    user = None
    for x in users:
        user = x
    if not(user.password == hashlib.sha224(password).hexdigest()):
        return None
    return user

def getUserNoPassword(username):
    if not(username):
        return None
    users = Users.query(Users.username == username)
    for user in users:
        return user
    return None


#Options
#   Number of Semesters
#   Names of Semesters
#   Academic or Calendar Year?
#   Time Zone
BATCH_SIZE = 10 # ideal batch size may vary based on entity size.

def UpdateSchema(cursor=None, num_updated=0):

    time =Settings.query()
    a, cur, more =time.fetch_page(10)
    if cursor:
       # query.fetch_page(BATCH_SIZE, start_cursor=cursor)
        time.fetch_page(BATCH_SIZE, start_cursor=cursor)

    to_put = []
    for p in time:
        # In this example, the default values of 0 for num_votes and avg_rating
        # are acceptable, so we don't need this loop.  If we wanted to manually
        # manipulate property values, it might go something like this:
        #Users.aboutme = 'iwin'
        p.tutorrate=10;
        to_put.append(p)
        #query.put()

    if to_put:
        ndb.put_multi(to_put)
        #ndb.delete_multi([m.mamadouisawsome for m in
  #query])
        num_updated += len(to_put)
        logging.debug(
            'Put %d entities to Datastore for a total of %d',
            len(to_put), num_updated)
        deferred.defer(
            UpdateSchema, cursor=time.fetch_page(BATCH_SIZE), num_updated=num_updated)
    else:
        logging.debug(
            'UpdateSchema complete with %d updates!', num_updated)
